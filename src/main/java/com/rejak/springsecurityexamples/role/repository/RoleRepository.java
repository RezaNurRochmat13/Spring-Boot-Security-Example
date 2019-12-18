package com.rejak.springsecurityexamples.role.repository;

import com.rejak.springsecurityexamples.role.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao, Integer> {
    
}
