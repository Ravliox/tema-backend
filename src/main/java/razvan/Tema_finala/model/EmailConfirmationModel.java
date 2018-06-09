package razvan.Tema_finala.model;

public class EmailConfirmationModel {
    private String email;
    private String confirmationCode;

    public EmailConfirmationModel() { }

    public EmailConfirmationModel(String email, String confirmationCode) {
        this.email = email;
        this.confirmationCode = confirmationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    @Override
    public String toString() {
        return "EmailConfirmationModel{" +
                "email='" + email + '\'' +
                ", confirmationCode=" + confirmationCode +
                '}';
    }
}
