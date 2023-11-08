package api.data;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class CommonPartOfRequest {
    static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    public RequestSpecification specification() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }
}
