package top.testeru.shizhan;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.testeru.base.An_Base;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description Disabled
 *
 * @createTime 2022年11月29日 20:29:00
 */
@DisplayName("😊计算器测试用例 ╯°□°）╯")
public class An_06Disable_Test extends An_Base {

// --- sum ---

    @Test
    @DisplayName("加法🐶")
    @Disabled
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
    @DisplayName("加法边界值😱")
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
    @DisplayName("加法异常😐")
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
    @DisplayName("减法🐶")
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
    @DisplayName("减法边界值😱")
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
    @DisplayName("减法异常😐")
    public void subtractBoundaryError(){
        logger.info("Begin Subtract Test");
        //超过边界值的减法运算
        Exception illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> mySUT.subtract(100, -98));
        // expected:期望值,  actual:运算的实际值
        Assertions.assertTrue(illegalArgumentException.getMessage().contains("enter an integer in the range"));
    }

}
