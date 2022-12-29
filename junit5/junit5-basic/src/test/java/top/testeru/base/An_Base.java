package top.testeru.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description
 * @createTime 2022年11月29日 20:24:00
 */
public class An_Base {
    public static final Logger logger = getLogger(lookup().lookupClass());
    public static MySUT mySUT;


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
    public void afterEach(){
        //调用类对象的销毁ID方法 - destroyId()
        mySUT.destroyId();
    }

    @AfterAll
    public static void afterAll(){
        mySUT.close();
    }

}
