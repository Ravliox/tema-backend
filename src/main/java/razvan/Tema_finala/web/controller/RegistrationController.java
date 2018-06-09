package razvan.Tema_finala.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import razvan.Tema_finala.database.entity.User;
import razvan.Tema_finala.service.NotificationService;

@RestController
public class RegistrationController {


    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/signup")
    public String singup(){
        return "Please sing up for our service";

    }

    @RequestMapping("/signup-success")
    public String signupSuccess(){

        //create user
        User user = new User();
        user.setFirstName("Stanica");
        user.setLastName("Razvan");
        user.setEmail("3.stanica.razvan@gmail.com");


        //send a notification
        return "Thank for your registering with us";
    }




}
