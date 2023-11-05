package order;

import api.data.order.response.OrderCheckResponse;
import api.data.order.response.OrderTakeResponse;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Test;
import api.data.random.RandomData;
import api.data.user.UserRegistration;
import api.data.user.response.UserCheckResponse;
import api.data.user.response.UserTakeResponse;
import io.qameta.allure.junit4.DisplayName;

import java.util.HashMap;


public class CreateOrderTest {
    UserTakeResponse userTakeResponse = new UserTakeResponse();
    UserCheckResponse userCheckResponse = new UserCheckResponse();
    OrderTakeResponse orderTakeResponse = new OrderTakeResponse();
    OrderCheckResponse orderCheckResponse = new OrderCheckResponse();
    private HashMap<String, String> ingredients = new HashMap<>();
    private String key = "ingredients";
    private String accessToken;
    Gson gson = new Gson();

    @DisplayName("Создать заказ авторизованным пользователем")
    @Test
    public void createOrderAuthorizedUser() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken =  userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        ingredients.put(key, "61c0c5a71d1f82001bdaaa75");
        String json = gson.toJson(ingredients);
        orderCheckResponse.successfulCreatedOrder(orderTakeResponse.createOrderResponse(json, accessToken.substring(7)));
    }

    //Тест построен на основе утверждения "Только авторизованные пользователи могут делать заказы." в разделе Авторизация и регистрация на странице 5.
    @DisplayName("Создать заказ неавторизованным пользователем")
    @Test
    public void createOrderUnauthorizedUser() {
        accessToken = "";
        ingredients.put(key, "61c0c5a71d1f82001bdaaa75");
        String json = gson.toJson(ingredients);
        orderCheckResponse.createOrdersUnauthorizedUser(orderTakeResponse.createOrderResponse(json, accessToken));
    }

    @DisplayName("Создать заказ (авторизованным пользователем) с неверным хешем ингредиента")
    @Test
    public void createOrderAuthorizedUserWithInvalidIngredientId() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken =  userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        ingredients.put(key, RandomData.randomInvalidIngredientId());
        String json = gson.toJson(ingredients);
        orderCheckResponse.orderWithInvalidIngredientId(orderTakeResponse.createOrderResponse(json, accessToken.substring(7)));
    }

    @DisplayName("Создать заказ (авторизованным пользователем) без ингредиентов")
    @Test
    public void createOrderAuthorizedUserWithoutIngredients() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken =  userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        ingredients.put(key, "");
        String json = gson.toJson(ingredients);
        orderCheckResponse.orderWithoutIngredients(orderTakeResponse.createOrderResponse(json, accessToken.substring(7)));
    }

    @After
    public void userDelete(){
        if(accessToken != null && !accessToken.isEmpty()){
            userCheckResponse.successfulDelete(userTakeResponse.deleteUserResponse(accessToken.substring(7)));
        }
    }
}
