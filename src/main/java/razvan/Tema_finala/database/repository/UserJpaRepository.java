package razvan.Tema_finala.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import razvan.Tema_finala.database.entity.User;

import javax.swing.*;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
