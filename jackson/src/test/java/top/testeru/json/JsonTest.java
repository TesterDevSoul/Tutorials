//package top.testeru.json;
//
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author www.testeru.top
// * @version 1.0.0
// * @Project junit5-tutorial
// * @Description
// * @createTime 2022年12月01日 11:06:00
// */
//public class JsonTest {
//    @Test
//    void orderListMap() throws IOException {
//        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
//        TypeReference<List<HashMap<String, Object>>> typeReference =
//                new TypeReference<List<HashMap<String, Object>>>() {};
//        mapper.findAndRegisterModules();
//        List<HashMap<String, Object>> listHashMap =
//                mapper.readValue(
//                        new File("src/test/resources/json/orderlist.json"),
//                        typeReference);
//
//        System.out.println(listHashMap.toString());
//    }
//    @Test
//    void orderLinesTest() throws IOException {
//        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
//        TypeReference<List<OrderLine>> typeReference =
//                new TypeReference<List<OrderLine>>() {};
//        mapper.findAndRegisterModules();
//        List<OrderLine> orderLines =
//                mapper.readValue(
//                        new File("src/test/resources/json/orderlist.json"),
//                        typeReference);
//
//        System.out.println(orderLines);
//    }
//    @Test
//    void orderMap() throws IOException {
//        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
//        TypeReference<HashMap<String, Object>> typeReference =
//                new TypeReference<HashMap<String, Object>>() {};
//        mapper.findAndRegisterModules();
//        HashMap<String, Object> stringObjectHashMap =
//                mapper.readValue(
//                        new File("src/test/resources/json/order.json"),
//                        typeReference);
//
//        System.out.println(stringObjectHashMap.toString());
//    }
//    @Test
//    void orderTest() throws IOException {
//        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
//        TypeReference<Order> typeReference =
//                new TypeReference<Order>() {};
//        mapper.findAndRegisterModules();
//        Order order =
//                mapper.readValue(
//                        new File("src/test/resources/json/order.json"),
//                        typeReference);
//
//        System.out.println(order);
//    }
//}
