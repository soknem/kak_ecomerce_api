package com.ecomerce.api.feature.user;

import com.ecomerce.api.domain.Role;
import com.ecomerce.api.domain.User;
import com.ecomerce.api.feature.role.RoleRepository;
import com.ecomerce.api.feature.user.dto.UserRequest;
import com.ecomerce.api.feature.user.dto.UserResponse;
import com.ecomerce.api.feature.user.dto.UserUpdateRequest;
import com.ecomerce.api.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public void createUser(UserRequest userRequest) {

        //map user from dto
        User user = userMapper.fromRequest(userRequest);
        user.setUuid(UUID.randomUUID().toString());

        //validate role by role name in database
        Role role = roleRepository.findByRoleName(userRequest.roleName()).orElse(null);

        if (role == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role = %s has not been found",
                    userRequest.roleName()));
        }

        //set role to  user
        user.setRole(role);

        //save to database
        userRepository.save(user);

    }

    @Override
    public Page<UserResponse> getAllUsers(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<User> userPage = userRepository.findAll(pageRequest);

        return userPage.map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse getUserByUuid(String uuid) {

        User user =
                userRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", uuid)));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUserByUuid(String uuid, UserUpdateRequest userUpdateRequest) {

        User user =
                userRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", uuid)));

        userMapper.updateUserRequest(user, userUpdateRequest);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUserByUuid(String uuid) {

        User user =
                userRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", uuid)));

        userRepository.delete(user);

    }
}
