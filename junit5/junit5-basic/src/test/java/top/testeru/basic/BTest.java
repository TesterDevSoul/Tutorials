package top.testeru.basic;

import org.junit.jupiter.api.*;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5
 * @Description
 * @createTime 2023年01月05日 17:18:45
 */
public class BTest extends ATest{
    @AfterAll
    public static void b_afterAll(){
        logger.info("B类AfterAll注解");
    }
    @AfterEach
    public void b_afterEach(){
        logger.info("B类AfterEach注解");
    }

    @BeforeEach
    public void b_beforeEach(){
        logger.info("B类BeforeEach注解");
    }
    @BeforeAll
    public static void b_beforeAll(){
        logger.info("B类BeforeAll注解");
    }


    @Test
    public void b1(){
        logger.info("B类第一个测试用例");
    }
    @Test
    public void b2(){
        logger.info("B类第二个测试用例");
    }
    @Test
    public void b3(){
        logger.info("B类第三个测试用例");
    }
}
