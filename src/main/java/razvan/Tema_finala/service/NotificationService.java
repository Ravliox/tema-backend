package razvan.Tema_finala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import razvan.Tema_finala.database.ProvisoryEntity.ProvisoryUser;
import razvan.Tema_finala.database.entity.User;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(ProvisoryUser provisoryUser){
        // send email
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(provisoryUser.getEmail());
//        mail.setFrom("3.stanica.razvan@gmail.com");
//        mail.setSubject("Unique code");
//        mail.setText(provisoryUser.getConfirmationCode() + "");
//
//        javaMailSender.send(mail);

    }




}
