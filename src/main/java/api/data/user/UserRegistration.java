package api.data.user;

public class UserRegistration {
    private String email;
    private String password;
    private String name;

    public UserRegistration(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public UserRegistration() {
    }

    public String getLogin() {
        return email;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
