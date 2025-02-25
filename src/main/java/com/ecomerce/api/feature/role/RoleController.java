package com.ecomerce.api.feature.role;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.feature.role.dto.RoleRequest;
import com.ecomerce.api.feature.role.dto.RoleResponse;
import com.ecomerce.api.feature.role.dto.RoleUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@Slf4j
@RequiredArgsConstructor
public class RoleController {

    private  final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRole(@Valid @RequestBody RoleRequest roleRequest){

        roleService.createRole(roleRequest);

    }

    @GetMapping
    public Page<RoleResponse> getAllRoles(
            @RequestParam (defaultValue = "0") int pageNumber,
            @RequestParam (defaultValue = "25") int pageSize){

        return roleService.getAllRoles(pageNumber,pageSize);
    }

    @GetMapping("/{roleName}")
    public RoleResponse getRoleByName(@PathVariable String roleName){

        return roleService.getRoleRoleName(roleName);
    }

    @PutMapping("/{roleName}")
    public RoleResponse updateByRoleName(@PathVariable String roleName,
                                  @Valid @RequestBody RoleUpdateRequest roleUpdateRequest){

      return   roleService.updateRoleByRoleName(roleName,roleUpdateRequest);
    }

    @DeleteMapping("/{roleName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByRoleName(@PathVariable String roleName){

        roleService.deleteRoleByRoleName(roleName);
    }
}
