package razvan.Tema_finala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razvan.Tema_finala.database.ProvisoryEntity.ProvisoryUser;
import razvan.Tema_finala.database.entity.User;
import razvan.Tema_finala.database.repository.UserJpaRepository;
import razvan.Tema_finala.model.UserModel;
import razvan.Tema_finala.model.exception.AuthorizationException;

import java.util.List;


@Service
public class ValidationService {


    @Autowired
    private UserJpaRepository userJpaRepository;

    public void validateEmail (String email) throws AuthorizationException {
        String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        if(!email.matches(emailRegex) && email != ""){
            throw new AuthorizationException("Invalid email format");
        }
    }

    public void validateName (String name) throws AuthorizationException {
        String nameRegex = "^[a-zA-Z]+$";
//        if(!name.matches(nameRegex) && name != ""){
//            throw new AuthorizationException("Invalid name format");
//        }

    }

    public void passwordValidation (String password) throws AuthorizationException {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        if(!password.matches(passwordRegex) && password != ""){
            throw new AuthorizationException("Invalid password format(Minimum eight characters, at least one uppercase letter and one number)");
        }

    }

    public void checkEmail (String email) throws AuthorizationException{
        List<User> users = userJpaRepository.findAll();
        for(User u : users ){
            if(u.getEmail().equals(email)){
                throw new AuthorizationException("This email already exist");
            }
        }
    }

    public void notNull(UserModel userModel) throws AuthorizationException{
        if(userModel.getFirstName() == "" |
           userModel.getLastName() == "" |
           userModel.getEmail() == "" |
           userModel.getPassword() == "" |
           userModel.getBirthDate() == null ) {
//            throw new AuthorizationException("All fields are mandatory!");
        }

    }

    public boolean validationCode (String code,  String code2) throws  AuthorizationException{
        if(!code.equals(code2) ){
            throw new AuthorizationException("Confirmation code is incorrect");
        }
        return true;
    }


}


