package top.testeru.yaml.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project jackson
 * @Description 成员变量与YAML的key不一致
 * @createTime 2022年12月02日 10:48:00
 */
public class OrderKey {
    @JsonProperty("item")
    private String otherItem;
    @JsonProperty("quantity")
    private int qua;
    @JsonProperty("unitPrice")
    private BigDecimal price;
    @JsonProperty("orderDate")
    private LocalDate date;

    @Override
    public String toString() {
        return "OrderKey{" +
                "otherItem='" + otherItem + '\'' +
                ", qua=" + qua +
                ", price=" + price +
                ", date=" + date +
                '}';
    }

    public String getOtherItem() {
        return otherItem;
    }

    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }

    public int getQua() {
        return qua;
    }

    public void setQua(int qua) {
        this.qua = qua;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
