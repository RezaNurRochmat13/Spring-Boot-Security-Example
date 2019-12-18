package com.rejak.springsecurityexamples.role.usecase;

import com.rejak.springsecurityexamples.role.dao.RoleDao;

import java.util.List;
import java.util.Optional;

public interface RoleUsecase {
    List<RoleDao> findAllRole();
    Long countAllRole();
    Optional<RoleDao> findRoleById(Integer id);
    RoleDao createNewRole(RoleDao roleDaoPayload);
    RoleDao updateRole(RoleDao roleDaoPayload);
    void deleteRole(RoleDao roleDaoPayload);
}
