package top.testeru.basic;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import top.testeru.MySUT;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5
 * @Description @Test注解
 *
 * 1. @Test注解修饰的方法可直接运行「@Test注解 是方法上的」
 * 2. 一个测试类里可以有多个@Test注解修饰的方法
 * 3. @Test注解作用类似Java代码中的main()方法入口
 * 4. @Test注解修饰的方法没有返回值，即方法声明时为void
 * 5. @Test注解里面编写的内容是测试用例执行的具体内容及断言结果
 *
 *  assertEquals(expected, actual,String message)
 *  expected:期望值,  actual:运算的实际值,  message:断言失败的提示信息
 * @createTime 2023年01月04日 11:56:00
 */
public class An_01Test_Test {
    static final Logger logger = getLogger(lookup().lookupClass());

    @Test
    public void one(){
        System.out.println("第一个测试用例");
    }

    @Test
    public void sum() {
        //1、被测系统计算器创建并命名：My Basic Test Project
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
    @Test
    public void sumFail() {
        //1、被测系统计算器创建并命名：My Basic Test Project
        MySUT mySUT = new MySUT("My Basic Test Project");
        //2、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        //3、测试用例步骤调用 - sum()
        int result = mySUT.sum(4, 1);
        //4、打印结果日志 - Sum Result
        logger.info("Sum Result：{}",result);
        //5、测试用例结果验证
        //expected:期望值,  actual:运算的实际值, message:断言失败时的解释说明
        assertEquals(7,result,"4+1计算结果错误"+result);
        logger.info("断言失败");
    }


    @Test
    public void sumFailWithSupplier() {
        //1、被测系统计算器创建并命名：My Basic Test Project
        MySUT mySUT = new MySUT("My Basic Test Project");
        //2、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        //3、测试用例步骤调用 - sum()
        int result = mySUT.sum(4, 1);
        //4、打印结果日志 - Sum Result
        logger.info("Sum Result：{}",result);
        int expected = 7;
        //5、测试用例结果验证
        //expected:期望值,  actual:运算的实际值, messageSupplier:断言失败时解释说明，只有失败时才运行
        assertEquals(expected,result, ()->"4+1计算结果为："+result+"，期望结果为："+expected);
        logger.info("断言失败");

    }
}
