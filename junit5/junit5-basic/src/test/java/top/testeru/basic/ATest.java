package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5
 * @Description
 * @createTime 2023年01月05日 17:18:39
 */
public class ATest {
    static final Logger logger = getLogger(lookup().lookupClass());

    @AfterAll
    public static void a_afterAll(){
        logger.info("A类AfterAll注解");
    }
    @AfterEach
    public void a_afterEach(){
        logger.info("A类AfterEach注解");
    }

    @BeforeEach
    public void a_beforeEach(){
        logger.info("A类BeforeEach注解");
    }
    @BeforeAll
    public static void a_beforeAll(){
        logger.info("A类BeforeAll注解");
    }
}
