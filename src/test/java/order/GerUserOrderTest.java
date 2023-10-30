package order;

import api.data.order.response.OrderCheckResponse;
import api.data.order.response.OrderTakeResponse;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import api.data.random.RandomData;
import api.data.user.UserRegistration;
import api.data.user.response.UserCheckResponse;
import api.data.user.response.UserTakeResponse;

public class GerUserOrderTest {
    UserTakeResponse userTakeResponse = new UserTakeResponse();
    UserCheckResponse userCheckResponse = new UserCheckResponse();
    OrderTakeResponse orderTakeResponse = new OrderTakeResponse();
    OrderCheckResponse orderCheckResponse = new OrderCheckResponse();
    private String accessToken;

    @DisplayName("Получить список заказов авторизованного пользователя")
    @Test
    public void getOrdersAuthorizedUser() {
        UserRegistration randomUser = RandomData.randomUserRegistration();
        accessToken = userCheckResponse.successfulRegistration(userTakeResponse.createUserResponse(randomUser));
        orderCheckResponse.successfulGetOrders(orderTakeResponse.getUserOrdersResponse(accessToken.substring(7)));
    }

    @DisplayName("Получить список заказов без предварительной авторизации")
    @Test
    public void getOrdersUnauthorizedUser() {
        accessToken = "";
        orderCheckResponse.getOrdersUnauthorizedUser(orderTakeResponse.getUserOrdersResponse(accessToken));
    }

    @After
    public void userDelete(){
        if(accessToken != null && !accessToken.isEmpty()){
            userCheckResponse.successfulDelete(userTakeResponse.deleteUserResponse(accessToken.substring(7)));
        }
    }
}
