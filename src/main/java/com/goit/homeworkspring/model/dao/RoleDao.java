package com.goit.homeworkspring.model.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "role")
@Entity
@Component
public class RoleDao {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<UsersDao> users = new HashSet<>();
}