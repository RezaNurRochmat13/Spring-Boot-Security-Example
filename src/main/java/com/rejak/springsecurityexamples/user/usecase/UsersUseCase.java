package com.rejak.springsecurityexamples.user.usecase;

import com.rejak.springsecurityexamples.user.dao.Users;

import java.util.List;
import java.util.Optional;

public interface UsersUseCase {
    List<Users> findAllUsers();
    Optional<Users> findUserById(Integer id);
    Users createNewUser(Users payload);
    Users updateUser(Users payload);
    void deleteUser(Users payload);
}
