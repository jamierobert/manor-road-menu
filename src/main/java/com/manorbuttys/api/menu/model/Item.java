package com.manorbuttys.api.menu.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@DynamoDBDocument
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String name;
    private int count;
    private BigDecimal price;
}
