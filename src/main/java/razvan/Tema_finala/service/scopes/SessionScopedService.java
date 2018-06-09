package razvan.Tema_finala.service.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import razvan.Tema_finala.database.repository.UserJpaRepository;
import razvan.Tema_finala.model.UserModel;

import java.io.Serializable;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedService implements Serializable {

    private UserJpaRepository userRepository;

    private UserModel userModel;

    public UserModel getUserModel(){
        return userModel;

    }

    public void setUserModel(UserModel userModel){
        this.userModel = userModel;
        System.out.println("Login succes!");
    }


}
