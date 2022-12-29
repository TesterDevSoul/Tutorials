package top.testeru;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project jackson
 * @Description 基本测试类
 * @createTime 2022年12月01日 18:20:00
 */
public class BaseTest {
    public static final Logger logger = getLogger(lookup().lookupClass());
    /**
     * 清空log日志文件
     */
    @BeforeAll
    static void cleanLog() {
        logger.info("当前项目路径为：{}", System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir"));

        File[] files = file.listFiles();
        //1.tostring 结尾是endwith .log 说明是日志文件，那就删除
        Arrays
                .stream(files)
                .filter(file1 -> file1
                        .toString()
                        .endsWith(".log"))

                .filter(file1 -> !file1.toString().contains(
                        new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".log"))
                .collect(Collectors.toList())
                .forEach(file1 -> {
                    logger.info("要删除的文件名为：{}", file1.getName());
                    System.gc();
                    file1.delete();
                });
    }

}
