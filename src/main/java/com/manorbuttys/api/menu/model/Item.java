package com.manorbuttys.api.menu.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

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
