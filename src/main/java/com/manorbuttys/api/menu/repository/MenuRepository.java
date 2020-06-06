package com.manorbuttys.api.menu.repository;

import com.manorbuttys.api.menu.model.MenuSection;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface MenuRepository extends CrudRepository<MenuSection, String> {
    Optional<MenuSection> findById(String id);


}
