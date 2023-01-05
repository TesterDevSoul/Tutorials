package top.testeru.shizhan;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description  Test注解
 *
 * 1. @Test注解修饰的方法可直接运行「@Test注解 是方法上的」
 * 2. 一个测试类里可以有多个@Test注解修饰的方法
 * 3. @Test注解作用类似Java代码中的main()方法入口
 * 4. @Test注解修饰的方法没有返回值，即方法声明时为void
 * 5. @Test注解里面编写的内容是测试用例执行的具体内容及断言结果
 *
 *  assertEquals(expected, actual,String message)
 *  expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
 * @createTime 2022年11月29日 19:54:00
 */
public class An_01Test_Test {
    static final Logger logger = getLogger(lookup().lookupClass());

    /**
     * 正向，加法测试用例
     */
    @Test
    public void sum() {
        //1、被测系统命名为 - My Basic Test Project
        MySUT mySUT = new MySUT("My Basic Test Project");
        //2、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        //3、测试用例步骤调用 - sum()
        int result = mySUT.sum(4, 1);
        //4、打印结果日志 - Sum Result
        logger.info("Sum Result：{}",result);
        //5、测试用例结果验证
        //expected:期望值,  actual:运算的实际值
        assertEquals(5,result);
    }

    /**
     * 加法边界值的测试用例，没有超过边界值
     */
    @Test
    public void sumBoundary() {
        //1、被测系统命名为 - My Basic Test Project
        MySUT mySUT = new MySUT("My Basic Test Project");
        //2、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        //3、测试用例步骤调用 - sum()
        int result = mySUT.sum(99, -98);
        //4、打印结果日志 - Sum Result
        logger.info("Sum Result：{}",result);
        //5、测试用例结果验证
        assertEquals(1,result);
    }

    /**
     * 加法边界值的测试用例，超过边界值，异常测试用例
     */
    @Test
    public void sumBoundaryError() {
        //1、被测系统命名为 - My Basic Test Project
        MySUT mySUT = new MySUT("My Basic Test Project");
        //2、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        //3、测试用例步骤调用 - sum()

        /**
         * 异常断言：
         * assertThrows「抛出异常或异常的父类」：   expectedType  抛出的异常类型；   executable    异常业务
         * assertThrowsExactly「抛出当前异常类」
         */
        Exception throwException = assertThrows(RuntimeException.class, () -> mySUT.sum(100, 1));
//        Exception exception = assertThrowsExactly(RuntimeException.class, () -> mySUT.sum(100, 1));
        Exception exception = assertThrowsExactly(IllegalArgumentException.class, () -> mySUT.sum(100, 1));
        //4、测试用例结果验证
        assertTrue(exception.getMessage().contains("enter an integer in the range"));
    }
//    @Test
//    public void sumBoundaryError() {
//        //1、被测系统命名为 - My Basic Test Project
//        MySUT mySUT = new MySUT("My Basic Test Project");
//        //2、打印日志 - Begin Sum Test
//        logger.info("Begin Sum Test");
//         //3、测试用例步骤调用 - sum()
//         int result = mySUT.sum(100, -99);
//         //4、打印结果日志 - Sum Result
//         logger.info("Sum Result：{}",result);
//         //5、测试用例结果验证
//         // java.lang.IllegalArgumentException: Please enter an integer in the range!
//         assertEquals(5,result);
//    }
}
