package top.testeru.shizhan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
 * @Description
 * BeforeEach
 *  * 方法上注解 void返回值 与在代码中的前后顺序无关
 *  每次运算前进行 初始化ID 操作
 *  * 在每一个@Test注解修饰的方法之前运行一次；所以，当前测试类有多少个Test注解，BeforeEach注解修饰的方法就运行多少次
 *  * 作用：测试用例中，测试方法需要初始化的内容及属性「app/web端进入固定页面，回退到固定页面；重启app；删除某些产生的测试数据」
 *
 *  AfterEach
 *  * 方法上注解 void返回值 与在代码中的前后顺序无关
 *  每次运算后进行 销毁ID 操作
 *  * 在每一个@Test注解修饰的方法之后运行一次；所以，当前测试类有多少个Test注解，AfterEach注解修饰的方法就运行多少次
 *  * 无论@Test注解修饰的测试方法是否断言成功，@AfterEach方法的内容都去运行
 *  * 作用：测试用例中，测试方法需要初始化的内容及属性「app/web端进入固定页面，回退到固定页面；重启app；删除某些产生的测试数据」
 *
 *
 * @createTime 2022年11月29日 19:54:00
 */
public class An_02Each_Test {
    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    //1、被测系统命名为 - My Basic Test Project
    //Java：对象声明可以作为成员变量添加在类下面，不使用方法
    MySUT mySUT = new MySUT("My Basic Test Project");
    //但是在类下直接使用方法调用则会报错，无法使用。
    //logger.info("Begin Sum Test");
    @BeforeEach
    public void beforeEach(){
        logger.info("BeforeEach注解");
        //2、调用类对象的初始化ID方法 - initId()
        mySUT.initId();

    }
    @AfterEach
    public void afterEach(){
        //6、调用类对象的销毁ID方法 - destroyId()
        mySUT.destroyId();
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
        Assertions.assertEquals(5,result);

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
        Assertions.assertEquals(1,result);
    }
    @Test
    public void sumBoundaryError() {
        //3、打印日志 - Begin Sum Test
        logger.info("Begin Sum Test");
        // assertThrows 抛出异常或异常的父类
        // assertThrowsExactly  抛出当前异常类
        Exception throwException = Assertions.assertThrows(RuntimeException.class, () -> mySUT.sum(100, 1));
//        Exception exception = assertThrowsExactly(RuntimeException.class, () -> mySUT.sum(100, 1));
        Exception exception = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> mySUT.sum(100, 1));
        Assertions.assertTrue(exception.getMessage().contains("enter an integer in the range"));
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
        Assertions.assertEquals(-3,subtract);
    }
    @Test
    public void subtractBoundary(){
        logger.info("Begin Subtract Test");
        //4、测试用例步骤调用 - subtract() 减法运算
        int subtract = mySUT.subtract(99, 98);
        //5、打印结果日志 - Operation Result
        logger.info("Operation result：{}",subtract);

        // expected:期望值,  actual:运算的实际值
        Assertions.assertEquals(1,subtract);
    }
    @Test
    public void subtractBoundaryError(){
        logger.info("Begin Subtract Test");
        //超过边界值的减法运算
        Exception illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> mySUT.subtract(100, -98));
        // expected:期望值,  actual:运算的实际值
        Assertions.assertTrue(illegalArgumentException.getMessage().contains("enter an integer in the range"));
    }
}
