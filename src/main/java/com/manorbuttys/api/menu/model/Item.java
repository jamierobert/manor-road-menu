package com.manorbuttys.api.menu.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@DynamoDBDocument
public class Item {

    private String name;
    private int count;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
