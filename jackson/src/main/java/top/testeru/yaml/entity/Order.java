package top.testeru.yaml.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project jackson
 * @Description orderlist YAML文件对应实体类
 * 实体类成员变量 与 YAML文件中Map的key 一致
 * @createTime 2022年12月01日 18:27:00
 */
public class Order {
    private String item;
    private int quantity;
    private BigDecimal unitPrice;
    private LocalDate orderDate;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}
