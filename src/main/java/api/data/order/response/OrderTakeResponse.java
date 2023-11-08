package api.data.order.response;

import api.data.CommonPartOfRequest;
import io.restassured.response.ValidatableResponse;

public class OrderTakeResponse extends CommonPartOfRequest {
    static final String ORDERS_PATH = "/api/orders";
    public ValidatableResponse createOrderResponse(String ingredients, String accessToken) {
        if(accessToken != null && !accessToken.isEmpty()){
            return specification()
                    .auth().oauth2(accessToken)
                    .and()
                    .body(ingredients)
                    .post(ORDERS_PATH)
                    .then();
        }else{
            return specification()
                    .body(ingredients)
                    .post(ORDERS_PATH)
                    .then();
        }
    }

    public ValidatableResponse getUserOrdersResponse(String accessToken) {
        if(accessToken != null && !accessToken.isEmpty()){
            return specification()
                    .auth().oauth2(accessToken)
                    .get(ORDERS_PATH)
                    .then();
        }else{
            return specification()
                    .get(ORDERS_PATH)
                    .then();
        }
    }
}
