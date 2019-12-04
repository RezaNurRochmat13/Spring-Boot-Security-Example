package com.rejak.springsecurityexamples.user.repository;

import com.rejak.springsecurityexamples.user.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
