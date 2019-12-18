package com.rejak.springsecurityexamples.role.presenter;

import com.rejak.springsecurityexamples.exception.ResourceNotFound;
import com.rejak.springsecurityexamples.role.dao.RoleDao;
import com.rejak.springsecurityexamples.role.usecase.RoleUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class RolePresenter {
    @Autowired
    RoleUsecase roleUsecase;

    @GetMapping("roles")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getAllRole() {
        Map<String, Object> map = new HashMap<>();
        List<RoleDao> roleDaoList = roleUsecase.findAllRole();
        Long countAllRole = roleUsecase.countAllRole();

        map.put("total", countAllRole);
        map.put("count", roleDaoList.size());
        map.put("data", roleDaoList);
        return map;
    }

    @GetMapping("role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getDetailRole(@Valid @PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        Optional<RoleDao> optionalRoleDao = Optional.ofNullable(roleUsecase.findRoleById(id)
                .orElseThrow(() -> new ResourceNotFound("Role not found with id : " + id)));

        map.put("data", optionalRoleDao);
        return map;
    }

    @PostMapping("role")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createNewUserRole(@Valid @RequestBody RoleDao roleDaoPayload) {
        Map<String, Object> map = new HashMap<>();
        roleUsecase.createNewRole(roleDaoPayload);

        map.put("message", "Role created");
        map.put("created_role", roleDaoPayload);
        return map;
    }

    @PutMapping("role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> updateRole(@Valid @RequestBody RoleDao roleDaoPayload,
                                          @PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        RoleDao roleDao = roleUsecase.findRoleById(id)
                .orElseThrow(() -> new ResourceNotFound("Role not found with id : " + id));

        roleDao.setRoleName(roleDaoPayload.getRoleName());
        roleDao.setRoleDescription(roleDaoPayload.getRoleDescription());
        roleUsecase.updateRole(roleDao);

        map.put("message", "Role updated successfully");
        map.put("updated_role", roleDaoPayload);
        return map;
    }

    @DeleteMapping("role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> deleteRole(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        RoleDao roleDao = roleUsecase.findRoleById(id)
                .orElseThrow(() -> new ResourceNotFound("Role not found with id : " + id));
        roleUsecase.deleteRole(roleDao);

        map.put("message", "Role deleted successfully");
        map.put("updated_role", roleDao);
        return map;
    }

}
