package com.goit.homeworkspring.repository;

import com.goit.homeworkspring.model.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao, UUID> {
    List<RoleDao> findByNameLikeIgnoreCase(String name);
}