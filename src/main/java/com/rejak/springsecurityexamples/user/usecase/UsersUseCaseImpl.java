package com.rejak.springsecurityexamples.user.usecase;

import com.rejak.springsecurityexamples.user.dao.Users;
import com.rejak.springsecurityexamples.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersUseCaseImpl implements UsersUseCase {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Long countAllUsers() {
        return usersRepository.count();
    }

    @Override
    public Optional<Users> findUserById(Integer id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users createNewUser(Users payload) {
        return usersRepository.save(payload);
    }

    @Override
    public Users updateUser(Users payload) {
        return usersRepository.save(payload);
    }

    @Override
    public void deleteUser(Users payload) {
        usersRepository.delete(payload);
    }
}
