package com.manorbuttys.api.menu.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Builder
@DynamoDBTable(tableName = "Menu")
@AllArgsConstructor
@NoArgsConstructor
public class MenuSection implements Comparable<MenuSection> {

    private String id;
    private String name;
    private List<Item> items;
    private int sortOrder;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    @DynamoDBAttribute
    public List<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuSection that = (MenuSection) o;
        return sortOrder == that.sortOrder && id.equals(that.id) && name.equals(that.name) && items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, items, sortOrder);
    }

    @Override
    public int compareTo(MenuSection o) {
        return Integer.compare(sortOrder, o.sortOrder);
    }
}
