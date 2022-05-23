package hu.kissmt.webesrestapi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "OrderData")
public class Order {
    private @Id @GeneratedValue long id;
    private double netTotalPrice;
    private double grossTotalPrice;
    private @ElementCollection Map<String, String> products;

    public Order() {}

    public Order(double netTotalPrice, double grossTotalPrice, Map<String, String> products) {

        this.netTotalPrice = netTotalPrice;
        this.grossTotalPrice = grossTotalPrice;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public double getNetTotalPrice() {
        return netTotalPrice;
    }

    public double getGrossTotalPrice() {
        return grossTotalPrice;
    }

    public Map<String, String> getProducts() {
        return products;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNetTotalPrice(double netTotalPrice) {
        this.netTotalPrice = netTotalPrice;
    }

    public void setGrossTotalPrice(double grossTotalPrice) {
        this.grossTotalPrice = grossTotalPrice;
    }

    public void setProducts(Map<String, String> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", netTotalPrice=" + netTotalPrice +
                ", grossTotalPrice=" + grossTotalPrice +
                ", products=" + products +
                '}';
    }
}
