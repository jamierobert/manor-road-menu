package com.manorbuttys.api.menu.repository;

import com.manorbuttys.api.menu.model.Item;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface MenuRepository extends CrudRepository<Item, String> {
    Optional<Item> findById(String id);
}
