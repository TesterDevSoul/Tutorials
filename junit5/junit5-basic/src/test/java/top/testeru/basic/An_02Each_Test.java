package top.testeru.basic;

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
 *  * 作用：测试用例中，测试方法需要初始化的内容及属性「app/web端进入固定页面，进入到首页；重启app；删除某些产生的测试数据」
 *
 *  AfterEach
 *  * 方法上注解 void返回值 与在代码中的前后顺序无关
 *  每次运算后进行 销毁ID 操作
 *  * 在每一个@Test注解修饰的方法之后运行一次；所以，当前测试类有多少个Test注解，AfterEach注解修饰的方法就运行多少次
 *  * 无论@Test注解修饰的测试方法是否断言成功，@AfterEach方法的内容都去运行
 *  * 作用：测试用例中，测试方法需要销毁的内容及属性「app/web端回退到固定页面，首页；退出app；删除某些产生的测试数据；删除log日志」
 *
 *
 * @createTime 2023年01月04日 12:26:00
 */
public class An_02Each_Test {
    static final Logger logger = getLogger(lookup().lookupClass());
    //1、被测系统计算器创建并命名：My Basic Test Project
    MySUT mySUT = new MySUT("My Basic Test Project");
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
}
