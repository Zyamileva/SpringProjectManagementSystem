package com.goit.homeworkspring.repository;

import com.goit.homeworkspring.model.dao.UsersDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface UsersRepository extends JpaRepository<UsersDao, UUID> {
    List<UsersDao> findByLastnameLikeIgnoreCase(String lastname);
    List<UsersDao> findByEmailLikeIgnoreCase(String email);
}
