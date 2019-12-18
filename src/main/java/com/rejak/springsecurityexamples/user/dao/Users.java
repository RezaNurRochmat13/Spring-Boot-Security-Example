package com.rejak.springsecurityexamples.user.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rejak.springsecurityexamples.role.dao.RoleDao;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Users implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_age")
    private Integer userAge;

    @Column(name = "user_address")
    private String userAddress;

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id_role", nullable = true)
    private RoleDao userRole;
}
