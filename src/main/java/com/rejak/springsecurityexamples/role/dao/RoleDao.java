package com.rejak.springsecurityexamples.role.dao;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.rejak.springsecurityexamples.user.dao.Users;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class RoleDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRoles;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @OneToMany(targetEntity = Users.class, mappedBy = "userRole", fetch = FetchType.LAZY)
    private List<Users> user = new ArrayList<>();
}
