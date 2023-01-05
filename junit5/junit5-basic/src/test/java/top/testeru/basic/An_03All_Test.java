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
 * @createTime 2022年11月29日 19:54:00
 */
public class An_03All_Test {
    static final Logger logger = getLogger(lookup().lookupClass());

    static MySUT mySUT;
    @AfterAll
    //public void afterAll(){//JUnitException
    public static void afterAll(){
        logger.info("BeforeAll注解");
        //8、被测系统计算器 对象close关闭
        mySUT.close();
    }
    @AfterEach
    public void afterEach(){
        //logger.info("AfterEach注解");
        //7、销毁测试方法的唯一的用例ID 调用类对象的销毁ID方法 - destroyId()
        mySUT.destroyId();
    }
    @Test
    public void sum() {
        //4、测试用例步骤调用 - sum()
        int result = mySUT.sum(4, 1);
        //5、日志打印计算结果：Sum Result
        logger.info("Sum Result：{}",result);
        //6、断言计算结果是否正确 测试用例结果验证
        //expected:期望值,  actual:运算的实际值
        assertEquals(5,result);
    }
    @Test
    public void sumFail() {
        //4、测试用例步骤调用 - sum()
        int result = mySUT.sum(4, 1);
        //5、日志打印计算结果：Sum Result
        logger.info("Sum Result：{}",result);
        //6、断言计算结果是否正确 测试用例结果验证
        //expected:期望值,  actual:运算的实际值, message:断言失败时的解释说明
        assertEquals(7,result,"4+1计算结果错误"+result);
        logger.info("断言失败");
    }


    @Test
    public void sumFailWithSupplier() {
        //4、测试用例步骤调用 - sum()
        int result = mySUT.sum(4, 1);
        //5、日志打印计算结果：Sum Result
        logger.info("Sum Result：{}",result);
        int expected = 7;
        //6、断言计算结果是否正确 测试用例结果验证
        //expected:期望值,  actual:运算的实际值, messageSupplier:断言失败时解释说明，只有失败时才运行
        assertEquals(expected,result, ()->"4+1计算结果为："+result+"，期望结果为："+expected);
        logger.info("断言失败");
    }

    @BeforeEach
    public void beforeEach(){
        // logger.info("BeforeEach注解");
        //2、为当每个测试方法分配唯一的用例ID - 调用类对象的初始化ID方法 initId()
        mySUT.initId();
        //3、日志打印开始测试：Begin Sum Test
        logger.info("Begin Sum Test");
    }
    @BeforeAll
    //public void beforeAll(){ //JUnitException
    public static void beforeAll(){
        //logger.info("BeforeAll注解");
        //1、被测系统计算器创建并命名：My Basic Test Project
        mySUT = new MySUT("My Basic Test Project");
    }

}
