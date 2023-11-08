package api.data;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class CommonPartOfRequest {
    static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    // для логирования используем filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
    public RequestSpecification specification() {
        return given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }
}
