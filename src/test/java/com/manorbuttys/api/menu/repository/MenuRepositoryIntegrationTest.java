package com.manorbuttys.api.menu.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manorbuttys.api.menu.MenuApplication;
import com.manorbuttys.api.menu.model.MenuSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.util.List;

import static com.manorbuttys.api.menu.utils.TestConstants.buildMenuSection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MenuApplication.class, MenuRepository.class})
@WebAppConfiguration
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=test1",
        "amazon.aws.secretkey=test231"})
public class MenuRepositoryIntegrationTest {

    @Autowired
    private MenuRepository menuRepository;
    private DynamoDBMapper dynamoDBMapper;
    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        dynamoDBMapper.batchDelete(menuRepository.findAll());
    }

    @Test
    public void testMenuIsReturned() {
        MenuSection menuSection = buildMenuSection();

        menuRepository.save(menuSection);

        List<MenuSection> result = (List<MenuSection>) menuRepository.findAll();

        //Then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName(), is(menuSection.getName()));
    }

    @Test
    public void fillDb() throws Exception{
        List<MenuSection> menuSections = objectMapper.readValue(new File("src/test/resources/menu.json"), new TypeReference<List<MenuSection>>(){});
        menuSections.forEach(section -> menuRepository.save(section));
    }
}