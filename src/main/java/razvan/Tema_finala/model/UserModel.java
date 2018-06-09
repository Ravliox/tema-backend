package razvan.Tema_finala.model;

import java.util.Date;
import java.util.Objects;

public class UserModel {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private String password;

    public UserModel() {
    }

    public UserModel(Integer id, String firstName, String lastName, String email, Date birthDate, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
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
        return "UserModel{" +
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
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) &&
                Objects.equals(firstName, userModel.firstName) &&
                Objects.equals(lastName, userModel.lastName) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(birthDate, userModel.birthDate) &&
                Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, email, birthDate, password);
    }
}
