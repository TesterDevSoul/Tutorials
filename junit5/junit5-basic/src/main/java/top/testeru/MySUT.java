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
    String name;//用例名

    String id;//唯一ID标识

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
}