package razvan.Tema_finala.web.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import razvan.Tema_finala.model.EmailConfirmationModel;
import razvan.Tema_finala.model.UserModel;
import razvan.Tema_finala.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

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

//    @PutMapping("/Login")
//    public void login


}
