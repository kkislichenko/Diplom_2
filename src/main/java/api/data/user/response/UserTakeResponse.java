package api.data.user.response;

import api.data.CommonPartOfRequest;
import api.data.user.UserLogin;
import io.restassured.response.ValidatableResponse;
import api.data.user.UserRegistration;


public class UserTakeResponse extends CommonPartOfRequest {
    static final String AUTH_PATH = "/api/auth";
    public ValidatableResponse createUserResponse(UserRegistration user) {
        return specification()
                .body(user)
                .post(AUTH_PATH + "/register")
                .then().log().all();
    }

    public ValidatableResponse loginUserResponse(UserLogin user) {
        return specification()
                .body(user)
                .post(AUTH_PATH + "/login")
                .then().log().all();
    }
    public ValidatableResponse changeUserDataResponse(String newData, String accessToken) {
        if(accessToken != null && !accessToken.isEmpty()){
            return specification()
                    .auth().oauth2(accessToken)
                    .body(newData)
                    .patch(AUTH_PATH + "/user")
                    .then().log().all();
        }else{
            return specification()
                    .body(newData)
                    .patch(AUTH_PATH + "/user")
                    .then().log().all();
        }
    }
    public ValidatableResponse deleteUserResponse(String accessToken) {
        return specification()
                .auth().oauth2(accessToken)
                .delete(AUTH_PATH + "/user")
                .then().log().all();
    }
}
