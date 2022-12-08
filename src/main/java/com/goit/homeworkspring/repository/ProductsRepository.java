package com.goit.homeworkspring.repository;

import com.goit.homeworkspring.model.dao.ProductsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsDao, UUID> {

    List<ProductsDao> findByNameLikeIgnoreCase(String name);

}
