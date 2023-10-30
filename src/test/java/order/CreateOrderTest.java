package order;

import api.data.order.response.OrderCheckResponse;
import api.data.order.response.OrderTakeResponse;
import org.junit.After;
import org.junit.Test;
import api.data.random.RandomData;
import api.data.user.UserRegistration;
import api.data.user.response.UserCheckResponse;
import api.data.user.response.UserTakeResponse;
import io.qameta.allure.junit4.DisplayName;


public class CreateOrderTest {
    UserTakeResponse userTakeResponse = new UserTakeResponse();
    UserCheckResponse userCheckResponse = new UserCheckResponse();
    OrderTakeResponse orderTakeResponse = new OrderTakeResponse();
    OrderCheckResponse orderCheckResponse = new OrderCheckResponse();
    private String accessToken;

    @DisplayName("Создать заказ авторизованным пользователем")
    @Test
    public void createOrderAuthorizedUser() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken =  userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        String ingredients = "{\"ingredients\": [\"61c0c5a71d1f82001bdaaa75\"]}";
        orderCheckResponse.successfulCreatedOrder(orderTakeResponse.createOrderResponse(ingredients, accessToken.substring(7)));
    }

    //Тест построен на основе утверждения "Только авторизованные пользователи могут делать заказы." в разделе Авторизация и регистрация на странице 5.
    @DisplayName("Создать заказ неавторизованным пользователем")
    @Test
    public void createOrderUnauthorizedUser() {
        accessToken = "";
        String ingredients = "{\"ingredients\": [\"61c0c5a71d1f82001bdaaa75\"]}";
        orderCheckResponse.createOrdersUnauthorizedUser(orderTakeResponse.createOrderResponse(ingredients, accessToken));
    }

    @DisplayName("Создать заказ (авторизованным пользователем) с неверным хешем ингредиента")
    @Test
    public void createOrderAuthorizedUserWithInvalidIngredientId() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken =  userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        String ingredients = String.format("{\"ingredients\": [\"%s\"]}", RandomData.randomIngredientId());
        orderCheckResponse.orderWithInvalidIngredientId(orderTakeResponse.createOrderResponse(ingredients, accessToken.substring(7)));
    }

    @DisplayName("Создать заказ (авторизованным пользователем) без ингредиентов")
    @Test
    public void createOrderAuthorizedUserWithoutIngredients() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken =  userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        String ingredients = "{\"ingredients\": []}";
        orderCheckResponse.orderWithoutIngredients(orderTakeResponse.createOrderResponse(ingredients, accessToken.substring(7)));
    }

    @After
    public void userDelete(){
        if(accessToken != null && !accessToken.isEmpty()){
            userCheckResponse.successfulDelete(userTakeResponse.deleteUserResponse(accessToken.substring(7)));
        }
    }
}
