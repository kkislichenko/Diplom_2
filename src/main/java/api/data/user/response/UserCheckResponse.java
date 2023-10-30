package api.data.user.response;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserCheckResponse {
    public String successfulRegistration(ValidatableResponse response){
        return response.assertThat()
                .body("success", is(true))
                .body("user.email", notNullValue())
                .body("user.name", notNullValue())
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract().path("accessToken");
    }

    public void registrationWithoutLoginOrPasswordOrName(ValidatableResponse response){
        response.assertThat()
                .body("message", equalTo("Email, password and name are required fields"))
                .body("success", is(false))
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN);
    }
    public void registrationOfAlreadyRegistered(ValidatableResponse response){
        response.assertThat()
                .body("message", equalTo("User already exists"))
                .body("success", is(false))
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN);
    }
    public String successfulLogin(ValidatableResponse response){
        return response.assertThat()
                .body("success", is(true))
                .body("user.email", notNullValue())
                .body("user.name", notNullValue())
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract().path("accessToken");
    }
    public void loginWithInvalidLoginOrPassword(ValidatableResponse response){
        response.assertThat()
                .body("message", equalTo("email or password are incorrect"))
                .body("success", is(false))
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED);
    }
    public void successfulChangeUserData(ValidatableResponse response){
        response.assertThat()
                .body("success", is(true))
                .body("user.email", notNullValue())
                .body("user.name", notNullValue());
    }
    public void changeDataUnauthorizedUser(ValidatableResponse response){
        response.assertThat()
                .body("message", equalTo("You should be authorised"))
                .body("success", is(false))
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED);
    }

    public void successfulDelete(ValidatableResponse response){
        response.assertThat()
                .body("success", is(true));
    }
}
