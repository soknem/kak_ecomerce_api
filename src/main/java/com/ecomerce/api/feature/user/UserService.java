package com.ecomerce.api.feature.user;

import com.ecomerce.api.feature.user.dto.UserRequest;
import com.ecomerce.api.feature.user.dto.UserResponse;
import com.ecomerce.api.feature.user.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * service that handle user managements
 *
 * @author pov soknem
 * @since 2025
 */
public interface UserService {

    /**
     * create new user
     *
     * @param userRequest is the dto that contain all user information to create
     * @author pov soknem
     * @since 2025
     */
    void createUser(UserRequest userRequest);

    /**
     * get all users with pagination
     *
     * @param pageNumber is the...
     * @param pageSize   is the ...
     * @return {@link Page<UserResponse>}
     */
    Page<UserResponse> getAllUsers(int pageNumber, int pageSize);

    /**
     * get user by uuid
     *
     * @param uuid is the uuid of user to get
     * @return {@link UserResponse}
     * @author pov soknem
     * @since 2025
     */

    UserResponse getUserByUuid(String uuid);

    /**
     * update user by uuid
     *
     * @param uuid              is the uuid of user to update
     * @param userUpdateRequest is the dto that contain new data of user to update
     * @return {@link UserResponse}
     * @author pov soknem
     * @since 2025
     */

    UserResponse updateUserByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    /**
     * delete user by uuid
     *
     * @param uuid is the uuid of user to delete
     * @author pov soknem
     * @since 2025
     */
    void deleteUserByUuid(String uuid);
}
