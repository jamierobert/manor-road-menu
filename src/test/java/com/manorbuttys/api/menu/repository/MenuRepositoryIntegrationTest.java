package com.manorbuttys.api.menu.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.manorbuttys.api.menu.MenuApplication;
import com.manorbuttys.api.menu.model.Item;
import com.manorbuttys.api.menu.model.MenuSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { MenuApplication.class, MenuRepository.class })
@WebAppConfiguration
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=test1",
        "amazon.aws.secretkey=test231" })
public class MenuRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    MenuRepository menuRepository;

    @BeforeEach
    public void setup(){
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        dynamoDBMapper.batchDelete(menuRepository.findAll());
    }

    @Test
    public void testMenuIsReturned(){

        //Given
        Item item = new Item();
        item.setName("Devious Monkey");
        item.setCount(1);
        item.setPrice(BigDecimal.valueOf(2.5));

        List<Item> items = new ArrayList<>();
        items.add(item);

        MenuSection menuSection = new MenuSection();
        menuSection.setName("Dinner Menu");
        menuSection.setItems(items);

        menuRepository.save(menuSection);

        //When
        List<MenuSection> result = (List<MenuSection>) menuRepository.findAll();

        //Then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName(), is(menuSection.getName()));
    }
}