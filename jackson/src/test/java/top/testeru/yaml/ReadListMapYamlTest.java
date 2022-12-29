package top.testeru.yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.testeru.yaml.entity.Order;
import top.testeru.yaml.entity.OrderKey;
import top.testeru.yaml.entity.OrderLines;
import top.testeru.yaml.entity.OrderLinesKey;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static top.testeru.BaseTest.logger;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project jackson
 * @Description 读取List<Map>结构的yaml文件
 * YAML文件示例：orderlist
 *
 * @createTime 2022年12月01日 18:12:00
 */
public class ReadListMapYamlTest {
    @Test
    @DisplayName("基本数据类型List<Map>读YAML文件")
    void listMapTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String, Object>>> typeReference =
                new TypeReference<>() {};
        List<HashMap<String, Object>> hashMaps = mapper.readValue(
                new File("src/test/resources/yaml/orderlist.yaml"), typeReference);
        logger.info("read YAML file from List Contains Map : {}", hashMaps);
    }
    @Test
    @DisplayName("实体类读 List<Map> YAML文件")
    public void orderTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
//        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<List<Order>> typeReference = new TypeReference<>() {};
        List<Order> orderList = mapper.readValue(new File("src/test/resources/yaml/orderlist.yaml"), typeReference);
        logger.info("read YAML file from orderList Module : {}", orderList);
    }
    @Test
    @DisplayName("实体类与Key不同读 List<Map> YAML文件")
    public void orderKeyTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
//        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<List<OrderKey>> typeReference = new TypeReference<>() {};
        List<OrderKey> orderKeyList = mapper.readValue(new File("src/test/resources/yaml/orderlist.yaml"), typeReference);
        logger.info("read YAML file from orderKeyList Module : {}", orderKeyList);
    }

    @Test
    @DisplayName("基本数据类型Map<List<Map>>读YAML文件")
    void mapListMapTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String,List<HashMap<String, Object>>>> typeReference =
                new TypeReference<>() {};
        HashMap<String,List<HashMap<String, Object>>> mapListMap = mapper.readValue(
                new File("src/test/resources/yaml/orderlines.yaml"), typeReference);
        logger.info("read YAML file from Map Contains List-Map : {}", mapListMap);
        //{orderLines=[{unitPrice=1.23, item=No. 9 Sprockets, quantity=12, orderDate=2019-04-17}, {unitPrice=3.45, item=No. Widget (10mm), quantity=10, orderDate=2022-01-16}]}
    }
    @Test
    @DisplayName("实体类读 Map<List<Map>> YAML文件")
    public void orderLinesTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
//        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<OrderLines> typeReference = new TypeReference<>() {
        };
        OrderLines orderLines = mapper.readValue(new File("src/test/resources/yaml/orderlines.yaml"), typeReference);
        logger.info("read YAML file from OrderLines Module : {}", orderLines);
    }
    @Test
    @DisplayName("实体类与Key不同读 Map<List<Map>> YAML文件")
    public void orderLinesKeyTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //功能上等价的便捷方法： mapper.registerModules(mapper.findModules());
        //我们需要使用 findAndRegisterModules方法，以便 Jackson正确处理我们的日期
        //Jackson也可以自动搜索所有模块，不需要我们手动注册
//        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<OrderLinesKey> typeReference = new TypeReference<>() {
        };
        OrderLinesKey orderLinesKey = mapper.readValue(new File("src/test/resources/yaml/orderlines.yaml"), typeReference);
        logger.info("read YAML file from OrderLinesKey Module : {}", orderLinesKey);
    }

}
