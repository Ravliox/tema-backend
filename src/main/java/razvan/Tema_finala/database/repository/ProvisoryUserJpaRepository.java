package razvan.Tema_finala.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import razvan.Tema_finala.database.ProvisoryEntity.ProvisoryUser;

public interface ProvisoryUserJpaRepository extends JpaRepository<ProvisoryUser, Integer> {
    ProvisoryUser findByEmail(String email);
}
