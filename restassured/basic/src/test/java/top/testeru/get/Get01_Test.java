package top.testeru.get;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static org.hamcrest.Matchers.*;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project restassured-basic
 * @Description get请求
 *
 *  given()        请求参数、请求拦截器
 *  .when()        请求链接
 *  .then();       返回响应
 *  https://api.github.com
 *  雪球股票获取
 *  股票的gpath断言
 *
 * @createTime 2022年11月28日 15:19:00
 */

public class Get01_Test {
    /**
     * GET请求：不带参数
     */
    @Test
    public void getWithNoParamTest(){
        ValidatableResponse validatableResponse =
                //发送请求参数
                given()
                    //添加请求日志
                    .log().all()
                //发送请求类型
                .when()
                    //GET请求 .get(); POST请求 .post()
                    .get("https://demoqa.com/BookStore/v1/Books")
                //获取响应
                .then()
                    //添加响应日志
                    .log().all();
        //断言接口请求的状态码 是否为200
        validatableResponse.statusCode(200);
        //断言
        validatableResponse.body("books.pages",hasItems(234,254,238,460,278,254,472,352));
    }

    /**
     * 获取股票
     * 数字作为 BigDecimal 返回
     */
    @Test
    public void getWithJsonNumTest() {
            //发送请求参数
            given()
                //添加请求日志
                .log().all()
                //将 REST Assured 配置为使用将所有 Json 数字作为 BigDecimal 返回的 JsonConfig
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
            //发送请求类型
            .when()
                //GET请求 .get(); POST请求 .post()
                .get("https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=SH601231,SZ002299&_=1541640828575")
            //获取响应
            .then()
                //添加响应日志
                .log().all()
                //断言   23.71
                .body("data.current[0]",is(closeTo(new BigDecimal(19),new BigDecimal(20))));
    }

    /**
     * matchesJsonSchemaInClasspath 进行schema模版断言
     */
    @Test
    public void schemaTest() {
        ValidatableResponse validatableResponse =
                //发送请求参数
                given()
                    //添加请求日志
                    .log().all()
                //发送请求类型
                .when()
                    //GET请求 .get(); POST请求 .post()
                    .get("https://demoqa.com/BookStore/v1/Books")
                //获取响应
                .then()
                    //添加响应日志
                    .log().all();
        //断言
        validatableResponse
                .assertThat()
                .body(matchesJsonSchemaInClasspath("book-schema.json"));
    }


    /**
     * gpath表达式断言
     */
    @Test
    public void getStockJsonTest() {
            //发送请求参数
            given()
                //添加请求日志
                .log().all()
                //将 REST Assured 配置为使用将所有 Json 数字作为 BigDecimal 返回的 JsonConfig
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
            .when()
                //GET请求 .get(); POST请求 .post()
                .get("https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=SH601231,SZ002299,SH600015,SH601919,SH600941&_=")
            //获取响应
            .then()
                //添加响应日志
                .log().all()
                .body("data.findAll{ it.current < 19}.symbol",is(hasItems("SH600015", "SH601919", "SH601231")));
    }
}
