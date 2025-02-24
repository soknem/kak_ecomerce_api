package com.ecomerce.api.feature.role;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.feature.role.dto.RoleRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@Slf4j
public class RoleController {

    @PostMapping
    public void createRole(@Valid @RequestBody RoleRequest roleRequest){

        log.info("Request:{}",roleRequest);
    }

    @GetMapping
    public String getAllRoles(){

        return null;
    }

    @GetMapping("/{roleName}")
    public String getRoleByName(@PathVariable String roleName){
        return null;
    }

    @PutMapping("/{roleName}")
    public void updateByRoleName(@PathVariable String roleName){

    }

    @DeleteMapping("/{roleName}")
    public void deleteByRoleName(@PathVariable String roleName){

    }
}
