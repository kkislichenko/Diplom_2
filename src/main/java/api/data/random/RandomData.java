package api.data.random;

import org.apache.commons.lang3.RandomStringUtils;
import api.data.user.UserRegistration;

public class RandomData {

    public static UserRegistration randomUserRegistration(){
        return new UserRegistration((RandomStringUtils.randomAlphanumeric(10, 12)) + "@yandex.ru", "UserAutotestPassword", "UserAutotestName");
    }
    public static String randomEmail(){
        return (RandomStringUtils.randomAlphanumeric(10, 12)) + "@yandex.ru";
    }
    public static String randomIngredientId(){
        return (RandomStringUtils.randomAlphanumeric(24));
    }
}
