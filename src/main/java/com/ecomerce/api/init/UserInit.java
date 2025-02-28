package com.ecomerce.api.init;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.domain.User;
import com.ecomerce.api.feature.role.RoleRepository;
import com.ecomerce.api.feature.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void initUsers() {
        if (userRepository.count() == 0) {

            // Fetch roles
            Optional<Role> adminRole = roleRepository.findByRoleName("ADMIN");
            Optional<Role> staffRole = roleRepository.findByRoleName("STAFF");
            Optional<Role> customerRole = roleRepository.findByRoleName("CUSTOMER");

            if (adminRole.isEmpty() || staffRole.isEmpty() || customerRole.isEmpty()) {
                throw new IllegalStateException("Roles must be initialized first!");
            }

            // 1. Admin User
            User admin = new User();
            admin.setUuid(UUID.randomUUID().toString());
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setPhone("0123456789");
            admin.setAvatar("https://example.com/admin.jpg");
            admin.setAddress("Phnom Penh, Cambodia");
            admin.setIsDeleted(false);
            admin.setIsChangePassword(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);
            admin.setRole(adminRole.get());

            userRepository.save(admin);

            // 2. Staff User
            User staff = new User();
            staff.setUuid(UUID.randomUUID().toString());
            staff.setUsername("staff");
            staff.setEmail("staff@gmail.com");
            staff.setPassword(passwordEncoder.encode("Staff@123"));
            staff.setPhone("0987654321");
            staff.setAvatar("https://example.com/staff.jpg");
            staff.setAddress("Siem Reap, Cambodia");
            staff.setIsDeleted(false);
            staff.setIsChangePassword(true);
            staff.setAccountNonExpired(true);
            staff.setAccountNonLocked(true);
            staff.setCredentialsNonExpired(true);
            staff.setRole(staffRole.get());

            userRepository.save(staff);

            // 3. Customer User
            User customer = new User();
            customer.setUuid(UUID.randomUUID().toString());
            customer.setUsername("customer");
            customer.setEmail("customer@gmail.com");
            customer.setPassword(passwordEncoder.encode("Customer@123"));
            customer.setPhone("011223344");
            customer.setAvatar("https://example.com/customer.jpg");
            customer.setAddress("Battambang, Cambodia");
            customer.setIsDeleted(false);
            customer.setIsChangePassword(true);
            customer.setAccountNonExpired(true);
            customer.setAccountNonLocked(true);
            customer.setCredentialsNonExpired(true);
            customer.setRole(customerRole.get());

            userRepository.save(customer);
        }
    }
}
