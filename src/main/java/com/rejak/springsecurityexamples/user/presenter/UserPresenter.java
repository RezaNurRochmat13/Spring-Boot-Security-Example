package com.rejak.springsecurityexamples.user.presenter;

import com.rejak.springsecurityexamples.user.dao.Users;
import com.rejak.springsecurityexamples.user.exception.ResourceNotFound;
import com.rejak.springsecurityexamples.user.usecase.UsersUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserPresenter {
    @Autowired
    UsersUseCaseImpl usersUseCase;

    @GetMapping("users")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> map = new HashMap<>();
        List<Users> users = usersUseCase.findAllUsers();

        map.put("total", users.size());
        map.put("count", users.size());
        map.put("data", users);
        return map;
    }

    @GetMapping("user/{id}")
    public Map<String, Object> getDetailUser(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Users findUserById = usersUseCase.findUserById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id : " + id));

        map.put("data", findUserById);
        return map;
    }

    @PostMapping("user")
    public Map<String, Object> createNewUser(@Valid @RequestBody Users users) {
        Map<String, Object> map = new HashMap<>();

        usersUseCase.createNewUser(users);

        map.put("message", "User created successfully");
        map.put("created_user", users);
        return map;
    }

    @PutMapping("user/{id}")
    public Map<String, Object> updateUser(@Valid @RequestBody Users users,
                                          @PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Users findUserById = usersUseCase.findUserById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id :" + id));

        findUserById.setUserName(users.getUserName());
        findUserById.setUserAge(users.getUserAge());
        findUserById.setUserAddress(users.getUserAddress());
        usersUseCase.updateUser(findUserById);

        map.put("message", "Users updated successfully");
        map.put("updated_user", users);
        return map;

    }

    @DeleteMapping("user/{id}")
    public Map<String, Object> deleteUser(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Users findUserById = usersUseCase.findUserById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id : " + id));
        usersUseCase.deleteUser(findUserById);

        map.put("message", "User deleted successfully");
        map.put("deleted_user", findUserById);
        return map;
    }
}
