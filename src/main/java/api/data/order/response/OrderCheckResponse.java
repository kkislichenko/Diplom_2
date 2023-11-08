package api.data.order.response;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class OrderCheckResponse {
    public void successfulCreatedOrder(ValidatableResponse response){
         response.assertThat()
                .body("success", is(true))
                .body("order.number", notNullValue())
                .body("name", notNullValue());
    }
    public void orderWithInvalidIngredientId(ValidatableResponse response){
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }
    public void orderWithoutIngredients(ValidatableResponse response) {
        response.assertThat()
                .body("message", equalTo("Ingredient ids must be provided"))
                .body("success", is(false))
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);
    }
    public void createOrdersUnauthorizedUser(ValidatableResponse response){
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED);
    }
    public void successfulGetOrders(ValidatableResponse response){
        response.assertThat()
                .body("orders", notNullValue())
                .body("success", is(true));
    }
    public void getOrdersUnauthorizedUser(ValidatableResponse response){
        response.assertThat()
                .body("message", equalTo("You should be authorised"))
                .body("success", is(false))
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED);
    }
}
