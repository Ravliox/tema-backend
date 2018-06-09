package razvan.Tema_finala.web.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import razvan.Tema_finala.model.EmailConfirmationModel;
import razvan.Tema_finala.model.LoginModel;
import razvan.Tema_finala.model.PostModel;
import razvan.Tema_finala.model.UserModel;
import razvan.Tema_finala.model.exception.AuthorizationException;
import razvan.Tema_finala.service.PostService;
import razvan.Tema_finala.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    //GetAllUsers
    @GetMapping
    public List<UserModel> getAllPersons()  {
        return userService.getAllUsers();
    }

    // createOrUpdate
    @PutMapping("/update")
    public void updateUserById( @RequestBody UserModel userModel)  {
        userService.updateUserById(userModel);
    }
    //Delete everything
    @DeleteMapping("/delete_all")
    public void deleteAll() {
        userService.deleteAll();
    }

    //confirmare inregistrare
    @PutMapping("/confirmation")
    public void emailConfirmation(@RequestBody EmailConfirmationModel emailConfirmationModel){
        userService.emailConfirmation(emailConfirmationModel);
    }

    //check if account is validated
    @PutMapping("/checkValidation")
    public void checkValidation (@RequestBody String email) throws NotFoundException {
        userService.checkValidation(email);
    }

    @PutMapping("/login")
    public void login (@RequestBody LoginModel loginModel) throws AuthorizationException {
        userService.login(loginModel.getEmail(), loginModel.getPassword());
    }

    @PutMapping("/post")
    public void post(@RequestBody PostModel postMessage) {
        this.postService.addPost(postMessage);

    }



}
