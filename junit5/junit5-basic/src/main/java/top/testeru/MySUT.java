package top.testeru;

import org.slf4j.Logger;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5
 * @Description 被测系统
 * @createTime 2023年01月04日 11:56:00
*/
public class MySUT {

    //获得具有所需名称的记录器
    static final Logger logger = getLogger(lookup().lookupClass());
    public static int result = 0;

    //用例名
    String name;
    //唯一ID标识
    String id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public MySUT(String name) {
        this.name = name;
        logger.info("Open {} ", name);
    }
    public void initId(){
        id = UUID.randomUUID().toString();
        logger.info("Generate ID：{} ", id);
    }
    public void destroyId() {
        if (id == null) {
            //没有初始化ID
            throw new IllegalArgumentException(name + " No Initialization ID");
        }
        logger.info("Release ID: {} ", id);
        id = null;
    }
    public void close() {
        logger.info("Close {} ", name);
    }
    //连续添加
    public int sum(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u == 100) | Arrays.stream(numbers).anyMatch(u -> u == -100)){
            //
            logger.warn("Enter an integer is 100！");
            throw new NumberFormatException("Enter an integer is 100！");
        }else if (Arrays.stream(numbers).anyMatch(u -> u > 99) |
                  Arrays.stream(numbers).anyMatch(u -> u < -99)){
            // 请输入范围内的整数
            logger.warn("Please enter an integer in the range!");
            throw new IllegalArgumentException("Please enter an integer in the range!");
        }else {
            return IntStream.of(numbers).sum();
        }
    }
    //从100进行减法
    public int subtract(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u > 99) |
                Arrays.stream(numbers).anyMatch(u -> u < -99)){
            logger.warn("Please enter an integer in the range!");
            throw new IllegalArgumentException("Please enter an integer in the range!");
        }else {
            return IntStream.of(numbers).reduce(100, (a, b) -> a-b);
        }
    }
    public int subtract(int x, int y) {
        if(x > 99 | x < -99 | y > 99 | y < -99){
            logger.warn("Please enter an integer in the range!");
            throw new IllegalArgumentException("Please enter an integer in the range!");
        }else {
            return x-y;
        }
    }
    //平均值 average
    public double average(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u > 99) |
                Arrays.stream(numbers).anyMatch(u -> u < -99)){
            logger.warn("Please enter an integer in the range!");
            throw new IllegalArgumentException("Please enter an integer in the range!");
        }else {
            return IntStream.of(numbers).average().getAsDouble();
        }
    }
    //连续拼接
    public String concatStr(String... words) {
        return String.join(" ", words);
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }

    public double div(double a, double b) {
        if (b == 0) {
            logger.warn("Divide by zero");
            throw new ArithmeticException("Divide by zero");
        } else {
            return a / b;
        }
    }
    public void clear(){
        result =0;
        logger.info("当前结果已清零！");
    }
}