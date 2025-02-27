package com.ecomerce.api.feature.role;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.feature.role.dto.RoleRequest;
import com.ecomerce.api.feature.role.dto.RoleResponse;
import com.ecomerce.api.feature.role.dto.RoleUpdateRequest;
import com.ecomerce.api.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public void createRole(RoleRequest roleRequest) {

        //validate role from request with role in database
        if (roleRepository.existsByRoleName(roleRequest.roleName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("role = %s already existed",
                    roleRequest.roleName()));
        }

        //map role from dto to entity
        Role role = roleMapper.fromRequest(roleRequest);

        //save data to database
        roleRepository.save(role);

    }

    @Override
    public RoleResponse getRoleRoleName(String roleName) {

        Role role =
                roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role = %s has been not found", roleName)));


        return roleMapper.toResponse(role);
    }

    @Override
    public Page<RoleResponse> getAllRoles(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Role> rolePage = roleRepository.findAll(pageRequest);

        return rolePage.map(roleMapper::toResponse);
    }

    @Override
    public RoleResponse updateRoleByRoleName(String roleName, RoleUpdateRequest roleUpdateRequest) {


        Role role =
                roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role = %s has been not found", roleName)));

        if (!Objects.equals(role.getRoleName(), roleUpdateRequest.roleName()) && roleRepository.existsByRoleName(roleUpdateRequest.roleName())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("role = %s already existed",
                    roleUpdateRequest.roleName()));
        }

        role.setRoleName(roleUpdateRequest.roleName());

        roleRepository.save(role);

        return new RoleResponse(role.getRoleName());
    }

    @Override
    public void deleteRoleByRoleName(String roleName) {

        Role role =
                roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role = %s has been not found", roleName)));

        roleRepository.delete(role);
    }
}
