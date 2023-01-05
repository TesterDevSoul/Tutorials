package top.testeru.base;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.MySUT;

import java.lang.reflect.Method;
import java.util.Optional;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description
 * @createTime 2022年11月29日 20:24:00
 */
public class An_TestInfoBase {
    public static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static MySUT mySUT;
    public String strResult;
    public int result ;


    @BeforeAll
    public static void beforeAll(){
        //被测系统命名为 - My Basic Test Project
        mySUT = new MySUT("My Basic Test Project");
    }
    @BeforeEach
    public void beforeEach(){
        //调用类对象的初始化ID方法 - initId()
        mySUT.initId();

    }
    @AfterEach
    public void afterEach(TestInfo testInfo){
        Optional<String> optional = testInfo
                .getTestMethod()
                .map(Method::getName)//获取方法名
                .filter(str -> str.contains("Num"));
        //str  Optional<null>


//        testInfo.getDisplayName()

        String reS = optional.isPresent() ? String.valueOf(result) : strResult ;


        //调用类对象的销毁ID方法 - destroyId()
        mySUT.destroyId();
    }

    @AfterAll
    public static void afterAll(){
        mySUT.close();
    }

}
