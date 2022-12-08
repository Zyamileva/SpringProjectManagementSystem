package com.goit.homeworkspring.repository;

import com.goit.homeworkspring.model.dao.ManufacturersDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ManufacturesRepository extends JpaRepository<ManufacturersDao, UUID> {

    List<ManufacturersDao> findByNameLikeIgnoreCase(String name);

}
