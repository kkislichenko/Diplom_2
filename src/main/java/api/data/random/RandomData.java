package api.data.random;

import com.github.javafaker.Faker;
import api.data.user.UserRegistration;
//import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public class RandomData {
    private static Faker faker = new Faker(new Locale("en"));

    public static String randomEmail(){
        //return (RandomStringUtils.randomAlphanumeric(10, 12)) + "@yandex.ru";
        return faker.internet().emailAddress();
    }

    public static UserRegistration randomUserRegistration(){
        //return new UserRegistration((RandomStringUtils.randomAlphanumeric(10, 12)) + "@yandex.ru", "UserAutotestPassword", "UserAutotestName");
        return new UserRegistration(randomEmail(), "UserAutotestPassword", "UserAutotestName");
    }

    public static String randomInvalidIngredientId(){
        //return (RandomStringUtils.randomAlphanumeric(24));
        return faker.random().hex(24);
    }
}
