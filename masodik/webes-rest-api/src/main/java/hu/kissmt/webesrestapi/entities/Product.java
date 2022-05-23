package hu.kissmt.webesrestapi.entities;

import javax.persistence.*;

@Entity
@Table(name = "ProductData")
public class Product {
    private @Id @GeneratedValue long id;
    private String productCode;
    private String productName;
    private double netUnitPrice;
    private double grossUnitPrice;

    public Product() {}

    public Product(String productCode, String productName, double netUnitPrice, double grossUnitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.netUnitPrice = netUnitPrice;
        this.grossUnitPrice = grossUnitPrice;
    }

    public long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public double getNetUnitPrice() {
        return netUnitPrice;
    }

    public double getGrossUnitPrice() {
        return grossUnitPrice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNetUnitPrice(double netUnitPrice) {
        this.netUnitPrice = netUnitPrice;
    }

    public void setGrossUnitPrice(double grossUnitPrice) {
        this.grossUnitPrice = grossUnitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", netTotalPrice=" + netUnitPrice +
                ", grossTotalPrice=" + grossUnitPrice +
                '}';
    }
}
