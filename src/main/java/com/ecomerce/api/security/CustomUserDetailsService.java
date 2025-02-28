    package com.ecomerce.api.security;

    import com.ecomerce.api.domain.User;
    import com.ecomerce.api.feature.user.UserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;


    @Service
    @RequiredArgsConstructor
    public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {

            // get the user from the database using the email or username
            User user = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));


            // create a custom user details object and set the user object to the custom user details object
            CustomUserDetails customUserDetails = new CustomUserDetails();
            // set the user object to the custom user details object
            customUserDetails.setUser(user);

            return customUserDetails;
        }

    }
