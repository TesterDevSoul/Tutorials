package top.testeru.basic;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description
 *
 * BeforeAll
 *  * 方法上注解 static修饰 void返回值 与在代码中的前后顺序无关
 *  * 在测试类里面运行一次，并且是在所有的方法之前运行一次
 *  * 作用：对象的声明 测试数据准备，log日志删除，apk安装，启动的某些参数的配置 AppiumDriver webdriver ChromeDriver
 *
 *  AfterAll
 *  * 方法上注解 static修饰 void返回值 与在代码中的前后顺序无关
 *  * 在测试类里面运行一次，并且是在所有的方法之后运行一次
 *  * 作用：apk卸载 app退出，测试用例结束，web端关闭浏览器操作。。。
 *
 * 总结：
 * 1、@Test注解对应的方法断言是否成功，AfterEach里面的代码都是运行的
 * 2、@AfterEach有多少个Test注解就运行多少次
 * 3、@BeforeAll、@BeforeEach、@Test、@AfterEach、@AfterAll 都是方法注解 只能写在方法上
 * 4、运行顺序：
 * @BeforeAll
 *       @BeforeEach   @Test   @AfterEach
 *       @BeforeEach   @Test   @AfterEach
 *       @BeforeEach   @Test   @AfterEach
 * @AfterAll
 * 5、注解是否可修饰同一方法
 * * @BeforeEach + @AfterEach   可以同时修饰一个方法
 * * @BeforeAll + @AfterAll     可以同时修饰一个方法
 * * @BeforeAll + @AfterEach    不可以❌
 * * @BeforeEach + @AfterAll    不可以❌
 *
 * @createTime 2022年11月29日 19:54:00
 */
public class An_03All_Test {
    static final Logger logger = getLogger(lookup().lookupClass());
    static MySUT mySUT;


    @BeforeAll
//    public void beforeAll(){//JUnitException
    public static void beforeAll(){
        //1、被测系统命名为 - My Basic Test Project
        mySUT = new MySUT("My Basic Test Project");
    }
    @BeforeEach
    public void beforeEach(){
        //2、调用类对象的初始化ID方法 - initId()
        mySUT.initId();

    }
    @AfterEach
    public void afterEach(){
        //6、调用类对象的销毁ID方法 - destroyId()
        mySUT.destroyId();
    }

    @AfterAll
//    public void afterAll(){//JUnitException
    public static void afterAll(){
        mySUT.close();
    }


// --- sum ---

    @Test
    public void sum() {
        //3、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        //4、测试用例步骤调用 - sum()
        int result = mySUT.sum(4, 1);
        //5、打印结果日志 - Sum Result
        logger.info("Sum Result：{}",result);
        //6、测试用例结果验证
        assertEquals(5,result);

    }

    @Test
    public void sumBoundary() {
        //3、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        //4、测试用例步骤调用 - sum()
        int result = mySUT.sum(99, -98);
        //5、打印结果日志 - Sum Result
        logger.info("Sum Result：{}",result);
        //6、测试用例结果验证
        assertEquals(1,result);
    }
    @Test
    public void sumBoundaryError() {
        //3、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        // assertThrows 抛出异常或异常的父类
        // assertThrowsExactly  抛出当前异常类
        Exception throwException = assertThrows(RuntimeException.class, () -> mySUT.sum(100, 1));
//        Exception exception = assertThrowsExactly(RuntimeException.class, () -> mySUT.sum(100, 1));
        Exception exception = assertThrowsExactly(IllegalArgumentException.class, () -> mySUT.sum(100, 1));
        assertTrue(exception.getMessage().contains("enter an integer in the range"));
    }


// --- subtract ---

    @Test
    public void subtract(){
        //3、打印日志 - Begin Subtract Test
        logger.info("Begin Subtract Test");
        //4、测试用例步骤调用 - subtract() 减法运算
        int subtract = mySUT.subtract(5, 8);
        //5、打印结果日志 - Operation Result
        logger.info("Operation result：{}",subtract);
        // expected:期望值,  actual:运算的实际值
        assertEquals(-3,subtract);
    }
    @Test
    public void subtractBoundary(){
        logger.info("Begin Subtract Test");
        //4、测试用例步骤调用 - subtract() 减法运算
        int subtract = mySUT.subtract(99, 98);
        //5、打印结果日志 - Operation Result
        logger.info("Operation result：{}",subtract);

        // expected:期望值,  actual:运算的实际值
        assertEquals(1,subtract);
    }
    @Test
    public void subtractBoundaryError(){
        logger.info("Begin Subtract Test");
        //超过边界值的减法运算
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> mySUT.subtract(100, -98));
        // expected:期望值,  actual:运算的实际值
        assertTrue(illegalArgumentException.getMessage().contains("enter an integer in the range"));
    }
}
