package com.rejak.springsecurityexamples.role.usecase;

import com.rejak.springsecurityexamples.role.dao.RoleDao;
import com.rejak.springsecurityexamples.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleUseCaseImpl implements RoleUsecase {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleDao> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Long countAllRole() {
        return roleRepository.count();
    }

    @Override
    public Optional<RoleDao> findRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public RoleDao createNewRole(RoleDao roleDaoPayload) {
        return roleRepository.save(roleDaoPayload);
    }

    @Override
    public RoleDao updateRole(RoleDao roleDaoPayload) {
        return roleRepository.save(roleDaoPayload);
    }

    @Override
    public void deleteRole(RoleDao roleDaoPayload) {
        roleRepository.delete(roleDaoPayload);
    }
}
