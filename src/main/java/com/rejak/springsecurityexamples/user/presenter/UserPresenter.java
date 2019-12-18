package com.rejak.springsecurityexamples.user.presenter;

import com.rejak.springsecurityexamples.mapper.ModelMapperConfig;
import com.rejak.springsecurityexamples.role.dao.RoleDao;
import com.rejak.springsecurityexamples.role.usecase.RoleUseCaseImpl;
import com.rejak.springsecurityexamples.user.dao.Users;
import com.rejak.springsecurityexamples.exception.ResourceNotFound;
import com.rejak.springsecurityexamples.user.dao.dto.DetailUserDto;
import com.rejak.springsecurityexamples.user.dao.dto.ListUserDto;
import com.rejak.springsecurityexamples.user.usecase.UsersUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class UserPresenter {
    @Autowired
    UsersUseCaseImpl usersUseCase;

    @Autowired
    RoleUseCaseImpl roleUseCase;

    @Autowired
    ModelMapperConfig modelMapperConfig;

    @GetMapping("users")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> map = new HashMap<>();
        List<Users> users = usersUseCase.findAllUsers();
        List<ListUserDto> listUserDtos = mapperToUserListDto(users);
        Long countAllUser = usersUseCase.countAllUsers();

        map.put("total", countAllUser);
        map.put("count", listUserDtos.size());
        map.put("data", listUserDtos);
        return map;
    }

    @GetMapping("user/{id}")
    public Map<String, Object> getDetailUser(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Optional<Users> findUserById = Optional.ofNullable(usersUseCase.findUserById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found with id : " + id)));
        DetailUserDto detailUserDto = mapperToUserDetailDto(findUserById);

        map.put("data", detailUserDto);
        return map;
    }

    @PostMapping("user/role/{role_id}")
    public Map<String, Object> createNewUser(@Valid @RequestBody Users users,
                                             @PathVariable Integer role_id) {
        Map<String, Object> map = new HashMap<>();
        RoleDao roleDao = roleUseCase.findRoleById(role_id)
                .orElseThrow(() -> new ResourceNotFound("Role not found with id : " + role_id));

        users.setUserRole(roleDao);
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

    // Mapper to ListUserDto
    private List<ListUserDto> mapperToUserListDto(List<Users> usersList) {
        List<ListUserDto> listUserDtos = new ArrayList<>();
        for(Users users: usersList) {
            ListUserDto listUserDto = modelMapperConfig.modelMapper().map(users, ListUserDto.class);
            listUserDto.setUserId(users.getUserId());
            listUserDto.setUserName(users.getUserName());

            listUserDtos.add(listUserDto);
        }
        return listUserDtos;
    }

    // Mapper to DetailUserDto
    private DetailUserDto mapperToUserDetailDto(Optional<Users> usersOptional) {
        DetailUserDto detailUserDto = modelMapperConfig.modelMapper().map(usersOptional, DetailUserDto.class);

        usersOptional.ifPresent(userMap -> {
            detailUserDto.setUserId(userMap.getUserId());
            detailUserDto.setUserName(userMap.getUserName());
            detailUserDto.setUserAge(userMap.getUserAge());
            detailUserDto.setUserAddress(userMap.getUserAddress());

            Optional<RoleDao> roleDaoOptional = roleUseCase.findRoleById(userMap.getUserRole().getIdRoles());
            roleDaoOptional.ifPresent(roleMap -> {
                detailUserDto.setUserRole(roleMap.getRoleName());
            });

        });
        return detailUserDto;
    }
}
