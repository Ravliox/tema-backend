package razvan.Tema_finala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razvan.Tema_finala.database.ProvisoryEntity.ProvisoryUser;
import razvan.Tema_finala.database.entity.User;
import razvan.Tema_finala.database.repository.ProvisoryUserJpaRepository;
import razvan.Tema_finala.database.repository.UserJpaRepository;
import razvan.Tema_finala.model.EmailConfirmationModel;
import razvan.Tema_finala.model.UserModel;
import razvan.Tema_finala.model.exception.AuthorizationException;
import razvan.Tema_finala.model.exception.NotFoundException;
import razvan.Tema_finala.service.scopes.SessionScopedService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private SessionScopedService sessionScopedService;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ProvisoryUserJpaRepository provisoryUserJpaRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private NotificationService notificationService;

    public UserModel convertToModels (User user){

        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setBirthDate(user.getBirthDate());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());

        return userModel;
    }

    public List<UserModel> convertToModelsList (List<User> users) {
        List<UserModel> userModels = new ArrayList<>();

        for (User u : users) {
            convertToModels(u);
            userModels.add(convertToModels(u));
        }

        return userModels;
    }

    //GetAllUsers
    public List<UserModel> getAllUsers(){
        List<User> user = userJpaRepository.findAll();
        return  convertToModelsList(user);
    }

    //CreateOrUpdate  <provisory user >
    public void updateUserById(UserModel userModel)  {
        validationService.validateEmail(userModel.getEmail());
        validationService.checkEmail(userModel.getEmail());
        validationService.validateName(userModel.getFirstName());
        validationService.validateName(userModel.getLastName());
        validationService.passwordValidation(userModel.getPassword());
        validationService.notNull(userModel);

        ProvisoryUser toUpdate = new ProvisoryUser();

        toUpdate.setId(userModel.getId());
        toUpdate.setFirstName(userModel.getFirstName());
        toUpdate.setLastName(userModel.getLastName());
        toUpdate.setBirthDate(userModel.getBirthDate());
        toUpdate.setEmail(userModel.getEmail());
        toUpdate.setPassword(userModel.getPassword());
        toUpdate.setConfirmationCode(this.generateUniqueCode());
        provisoryUserJpaRepository.save(toUpdate);
        notificationService.sendNotification(toUpdate);
    }

    // generate unique code
    public String generateUniqueCode(){
        Random rand = new Random();

        long random = (long)(rand.nextDouble()*1000000000L);
        return random + "";
    }

    public void deleteAll(){
        provisoryUserJpaRepository.deleteAll();
    }

    public void emailConfirmation(EmailConfirmationModel emailConfirmationModel){

        validationService.validateEmail(emailConfirmationModel.getEmail());
        ProvisoryUser provisoryUser = provisoryUserJpaRepository.findByEmail(emailConfirmationModel.getEmail());
        if( provisoryUser != null){
            validationService.validationCode(emailConfirmationModel.getConfirmationCode(),provisoryUser.getConfirmationCode());
        }
        if(validationService.validationCode(emailConfirmationModel.getConfirmationCode(),provisoryUser.getConfirmationCode()) == true){
            User user = new User();
            user.setId(provisoryUser.getId());
            user.setFirstName(provisoryUser.getFirstName());
            user.setLastName(provisoryUser.getLastName());
            user.setBirthDate(provisoryUser.getBirthDate());
            user.setEmail(provisoryUser.getEmail());
            user.setPassword(provisoryUser.getPassword());
            user.setLoginTries(0);
            userJpaRepository.save(user);
            provisoryUserJpaRepository.delete(provisoryUser);
        }

    }


    // 1.C

    public void checkValidation(String email) throws NotFoundException {
        ProvisoryUser provisoryUser = provisoryUserJpaRepository.findByEmail(email);
        if (provisoryUser == null){
            throw new NotFoundException("No user with this email! ");
        }
        validationService.validateEmail(provisoryUser.getEmail());
        validationService.checkEmail(provisoryUser.getEmail());

        if(email.matches(provisoryUser.getEmail())){
            provisoryUser.setConfirmationCode(this.generateUniqueCode());
            provisoryUserJpaRepository.save(provisoryUser);
            notificationService.sendNotification(provisoryUser);
        }
    }


    // 2.A Functionalitatea de SingIn

    public UserModel getLoggedUserDetails(){
        User loggedPerson = userJpaRepository.findByEmail(sessionScopedService.getUserModel().getEmail());
        return getUserModelFromPerson(loggedPerson);
    }

    public void login(String email, String password) throws AuthorizationException {
        User user = userJpaRepository.findByEmail(email);
        if(user == null){
            throw new AuthorizationException("Invalid credendtials");
        } else if (!user.getPassword().equals(password)) {
            user.setLoginTries(user.getLoginTries() + 1);
            if (user.getLoginTries() == 3){
                user.setBlocked(true);
                user.setLastTry(new Date());

            }
            userJpaRepository.save(user);
            throw new AuthorizationException("Invalid credendtials");

        }
        if (user.isBlocked()) {
            if (new Date().getTime() - user.getLastTry().getTime() < 30000) {
                throw new AuthorizationException("User is blocked for the next 60 minutes");
            }
        }

        user.setLoginTries(0);
        this.userJpaRepository.save(user);
        sessionScopedService.setUserModel(getUserModelFromPerson(user));
    }

    private UserModel getUserModelFromPerson(User user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setBirthDate(user.getBirthDate());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        return userModel;
    }



    // 3A Functionalitate postari


}
