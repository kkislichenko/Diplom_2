package api.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    private String email;
    private String password;

    public static UserLogin from(UserRegistration userRegistration) {
        return new UserLogin(userRegistration.getEmail(), userRegistration.getPassword());
    }

}
