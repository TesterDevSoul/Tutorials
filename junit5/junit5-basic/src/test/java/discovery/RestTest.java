package discovery;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-basic
 * @Description
 * @createTime 2022年12月01日 14:42:00
 */
public class RestTest {
    @Test
    public void te() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        TypeReference<HashMap<String,String>> typeReference = new TypeReference<HashMap<String,String>>(){};
        HashMap<String, String> login = objectMapper.readValue(new File("src/test/resources/user.json"), typeReference);

        //获取token

        Response response = given()
                .log().all()
                .contentType("application/json; charset=utf-8")
                .body(login)
                .queryParam("password", "123456")
                .queryParam("username", "admin")
                .when()
                .post("http://127.0.0.1:8085/auth/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        String token = response.path("data.accessToken").toString();
        System.out.println("token:"+token);

    }
}
