package razvan.Tema_finala.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import razvan.Tema_finala.database.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {


    @Query ("SELECT p from User u INNER JOIN u.my_posts p WHERE u.id = :id")
        List<Post> getPostsByUser_Id(@Param("id") Integer userId);
}
