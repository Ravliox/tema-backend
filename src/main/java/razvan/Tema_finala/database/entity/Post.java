package razvan.Tema_finala.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "my_post")
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize=500)
    @Column(name="id")
    private Integer id;

    @Column(name = "post_message")
    private String postMessage;

    @Column(name = "like")
    private Integer like;

    @Column(name = "dislike")
    private Integer dislike;

    @Column(name = "visibility")
    private boolean visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Post(Integer id, String text, boolean visibility) {
        this.id = id;
        this.postMessage = text;
        this.like = like;
        this.dislike = dislike;
        this.visibility = visibility;
    }

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String text) {
        this.postMessage = text;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + postMessage + '\'' +
//                ", like=" + like +
//                ", dislike=" + dislike +
                ", visibility=" + visibility +
                '}';
    }
}
