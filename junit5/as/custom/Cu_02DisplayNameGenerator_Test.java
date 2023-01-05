package top.testeru.custom;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import top.testeru.base.An_Base;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description 自定义显示名称
 * @createTime 2022年11月29日 20:29:00
 */
@DisplayNameGeneration(Cu_02DisplayNameGenerator_Test.ReplaceCamelCase.class)
public class Cu_02DisplayNameGenerator_Test extends An_Base {

    //标准显示名称生成行为 Standard默认配置
    static class ReplaceCamelCase extends DisplayNameGenerator.Standard {
        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return replaceCamelCase(super.generateDisplayNameForClass(testClass))+ "...";
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return replaceCamelCase(super.generateDisplayNameForNestedClass(nestedClass));
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            //+ DisplayNameGenerator.parameterTypesAsString(testMethod) 方法的括号
            return this.replaceCamelCase(testClass.getSimpleName()+ "；方法名：" + testMethod.getName()+ ".");
        }

        String replaceCamelCase(String camelCase) {
            StringBuilder result = new StringBuilder();
            result.append(camelCase.charAt(0));
            for (int i=1; i<camelCase.length(); i++) {
                if (Character.isUpperCase(camelCase.charAt(i))) {
                    result.append(' ');
                    result.append(Character.toLowerCase(camelCase.charAt(i)));
                } else {
                    result.append(camelCase.charAt(i));
                }
            }
            return result.toString().replace("test","Test");
        }
    }
// --- sum ---

    @Test
    public void sumtest() {
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
    public void sumtest_Boundary() {
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
    public void sumtest_Boundary_Error() {
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
    public void subtracttest(){
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
    public void subtracttestBoundary(){
        logger.info("Begin Subtract Test");
        //4、测试用例步骤调用 - subtract() 减法运算
        int subtract = mySUT.subtract(99, 98);
        //5、打印结果日志 - Operation Result
        logger.info("Operation result：{}",subtract);

        // expected:期望值,  actual:运算的实际值
        Assertions.assertEquals(1,subtract);
    }

    @Test
    public void subtracttestBoundaryError(){
        logger.info("Begin Subtract Test");
        //超过边界值的减法运算
        Exception illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> mySUT.subtract(100, -98));
        // expected:期望值,  actual:运算的实际值
        Assertions.assertTrue(illegalArgumentException.getMessage().contains("enter an integer in the range"));
    }

}
