package razvan.Tema_finala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razvan.Tema_finala.database.entity.Post;
import razvan.Tema_finala.database.repository.PostRepository;
import razvan.Tema_finala.database.repository.UserJpaRepository;
import razvan.Tema_finala.database.entity.User;
import razvan.Tema_finala.model.PostModel;
import razvan.Tema_finala.model.UserModel;
import razvan.Tema_finala.service.scopes.SessionScopedService;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserJpaRepository userRepository;

    @Autowired
    private SessionScopedService session;

    public void addPost(PostModel postModel){
        UserModel userModel = this.session.getUserModel();
        User user = this.userRepository.findByEmail(userModel.getEmail());
        Post post = new Post();
        post.setUser(user);
        post.setPostMessage(postModel.getPostMessage());
        post.setLike(0);
        post.setDislike(0);
        post.setVisibility( postModel.getPostMessage().equals("PUBLIC") ? true : false );

        this.postRepository.save(post);
    }


}
