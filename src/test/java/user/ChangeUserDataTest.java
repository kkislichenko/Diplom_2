package user;

import com.google.gson.Gson;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import api.data.random.RandomData;
import api.data.user.UserRegistration;
import api.data.user.response.UserCheckResponse;
import api.data.user.response.UserTakeResponse;

import java.util.HashMap;

@RunWith(Parameterized.class)
public class ChangeUserDataTest {
    UserTakeResponse userTakeResponse = new UserTakeResponse();
    UserCheckResponse userCheckResponse = new UserCheckResponse();
    private String accessToken;
    private String newData;
    private String key;
    private String value;

    public ChangeUserDataTest(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Parameterized.Parameters
    public static Object[] getOrderData() {
        return new Object[][] {
                {"email", RandomData.randomEmail()},
                {"password", "newPasswordAutotest"},
                {"name", "newNameAutotest"},
        };
    }

    @Before
    public void setUp() {
        HashMap<String, String> newDataForUser = new HashMap<>();
        newDataForUser.put(key, value);
        Gson gson = new Gson();
        newData = gson.toJson(newDataForUser);
    }

    @DisplayName("Изменить эл. почту, пароль, имя авторизованного пользователя")
    @Test
    public void changeDataAuthorizedUser() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken =  userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        userCheckResponse.successfulChangeUserData(userTakeResponse.changeUserDataResponse(newData, accessToken.substring(7)));
    }

    @DisplayName("Изменить эл. почту, пароль, имя пользователя без предварительной авторизации")
    @Test
    public void changeDataUnauthorizedUser() {
        accessToken = "";
        userCheckResponse.changeDataUnauthorizedUser(userTakeResponse.changeUserDataResponse(newData, accessToken));
    }

    @After
    public void userDelete(){
        if(accessToken != null && !accessToken.isEmpty()){
            userCheckResponse.successfulDelete(userTakeResponse.deleteUserResponse(accessToken.substring(7)));
        }
    }

}
