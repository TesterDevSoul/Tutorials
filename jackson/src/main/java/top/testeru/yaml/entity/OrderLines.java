package top.testeru.yaml.entity;

import java.util.List;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project jackson
 * @Description orderLines Key一致 对应YAML文件
 * @createTime 2022年12月02日 11:18:00
 */
public class OrderLines {
    private List<Order> orderLines;

    @Override
    public String toString() {
        return "OrderLines{" +
                "orderLines=" + orderLines +
                '}';
    }

    public List<Order> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<Order> orderLines) {
        this.orderLines = orderLines;
    }
}
