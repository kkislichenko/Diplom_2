package user;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import api.data.random.RandomData;
import api.data.user.UserLogin;
import api.data.user.UserRegistration;
import api.data.user.response.UserCheckResponse;
import api.data.user.response.UserTakeResponse;

public class LoginUserTest {
    UserTakeResponse userTakeResponse = new UserTakeResponse();
    UserCheckResponse userCheckResponse = new UserCheckResponse();
    private String accessToken;

    @DisplayName("Авторизоваться существующим пользователем")
    @Test
    public void loginUser() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        UserLogin user = UserLogin.from(randomUser);
        accessToken = userCheckResponse.successfulLogin(userTakeResponse.loginUserResponse(user));
    }

    @DisplayName("Авторизоваться с неверным логином и паролем")
    @Test
    public void loginWithInvalidData(){
        UserLogin user = new UserLogin(RandomData.randomEmail(),"invalidPassword");
        userCheckResponse.loginWithInvalidLoginOrPassword(userTakeResponse.loginUserResponse(user));
    }

    @After
    public void userDelete(){
        if(accessToken != null && !accessToken.isEmpty()){
            userCheckResponse.successfulDelete(userTakeResponse.deleteUserResponse(accessToken.substring(7)));
        }
    }
}
