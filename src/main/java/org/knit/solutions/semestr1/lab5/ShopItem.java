package org.knit.solutions.semestr1.lab5;

import java.util.Objects;

public class ShopItem {
    String name;
    String productType;
    double price;
    String partNumber;

    public ShopItem(String name, String productType, double price, String partNumber) {
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.partNumber = partNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getName() {
        return name;
    }

    public String getProductType() {
        return productType;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(partNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !obj.getClass().equals(this.getClass()))
            return false;

        ShopItem itemToCompare = (ShopItem) obj;
        return itemToCompare.getPartNumber().equals(this.getPartNumber());
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", productType='" + productType + '\'' +
                ", partNumber='" + partNumber + '\'' +
                '}';
    }
}
