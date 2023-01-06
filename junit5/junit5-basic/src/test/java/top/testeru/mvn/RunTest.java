package top.testeru.mvn;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.basic.ATest;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5
 * @Description 运行
 * @createTime 2023年01月05日 17:18:45
 */
public class RunTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    @AfterAll
    public static void b_afterAll(){
        logger.info("Run类AfterAll注解");
    }
    @AfterEach
    public void b_afterEach(){logger.info("Run类AfterEach注解");}
    @BeforeEach
    public void b_beforeEach(){
        logger.info("Run类BeforeEach注解");
    }
    @BeforeAll
    public static void b_beforeAll(){
        logger.info("Run类BeforeAll注解");
    }
    @Test
    public void b1(){
        logger.info("Run类第一个测试用例");
    }
    @Test
    public void b2(){
        logger.info("Run类第二个测试用例");
        //assertEquals(6,7,"断言失败的用例");
    }
    @Test
    public void b3(){
        logger.info("Run类第三个测试用例");
        //int a = 2 /0 ;
        //assertEquals(6,6,"断言失败的用例");
    }
}
