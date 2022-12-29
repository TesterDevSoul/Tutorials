package top.testeru.yaml.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project jackson
 * @Description orderLines Key一致 对应YAML文件
 * @createTime 2022年12月02日 11:18:00
 */
public class OrderLinesKey {
    @JsonProperty("orderLines")

    private List<Order> orders;

    @Override
    public String toString() {
        return "OrderLines{" +
                "orders=" + orders +
                '}';
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
