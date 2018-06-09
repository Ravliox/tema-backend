package razvan.Tema_finala.database.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(schema = "public", name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "password")
    private String password;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "last_try")
    private Date lastTry;

    @Column(name = "tries")
    private Integer loginTries ;

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    private List<Post> posts;


    public Date getLastTry() {
        return lastTry;
    }

    public void setLastTry(Date lastTry) {
        this.lastTry = lastTry;
    }


    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Integer getLoginTries() {
        return loginTries;
    }

    public void setLoginTries(Integer loginTries) {
        this.loginTries = loginTries;
    }

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email, Date birthDate, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;

        this.blocked = false;
        this.loginTries = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, email, birthDate, password);
    }
}
