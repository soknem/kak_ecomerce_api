package com.ecomerce.api.init;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.feature.role.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleInit {

    private final RoleRepository roleRepository;

    @PostConstruct
    void initRoles() {
        if (roleRepository.count() == 0) {
            List<String> roles = Arrays.asList("ADMIN", "STAFF", "CUSTOMER");

            for (String roleName : roles) {
                Role role = new Role();
                role.setRoleName(roleName);
                roleRepository.save(role);
            }
        }
    }
}
