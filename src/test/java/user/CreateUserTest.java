package user;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import api.data.random.RandomData;
import api.data.user.UserRegistration;
import api.data.user.response.UserCheckResponse;
import api.data.user.response.UserTakeResponse;

public class CreateUserTest {
    UserTakeResponse userTakeResponse = new UserTakeResponse();
    UserCheckResponse userCheckResponse = new UserCheckResponse();
    private String accessToken;

    @DisplayName("Создать уникального пользователя")
    @Test
    public void createUser() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken = userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
    }

    @DisplayName("Создать пользователя, который уже зарегистрирован")
    @Test
    public void duplicateUser() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken = userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        userCheckResponse.registrationOfAlreadyRegistered(userTakeResponse.createUserResponse(randomUser));
    }

    @DisplayName("Создать пользователя без эл. почты")
    @Test
    public void createWithoutEmail(){
        UserRegistration user = new UserRegistration("", "NewCourier", "withoutLogin");
        userCheckResponse.registrationWithoutLoginOrPasswordOrName(userTakeResponse.createUserResponse(user));
    }

    @DisplayName("Создать пользователя без пароля")
    @Test
    public void createWithoutPassword(){
        UserRegistration user = new UserRegistration(RandomData.randomEmail(), "", "withoutLogin");
        userCheckResponse.registrationWithoutLoginOrPasswordOrName(userTakeResponse.createUserResponse(user));
    }

    @DisplayName("Создать пользователя без имени")
    @Test
    public void createWithoutName(){
        UserRegistration user = new UserRegistration(RandomData.randomEmail(), "NewCourier", "");
        userCheckResponse.registrationWithoutLoginOrPasswordOrName(userTakeResponse.createUserResponse(user));
    }

    @After
    public void userDelete(){
        if(accessToken != null && !accessToken.isEmpty()){
            userCheckResponse.successfulDelete(userTakeResponse.deleteUserResponse(accessToken.substring(7)));
        }
    }
}
