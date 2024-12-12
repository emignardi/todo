package com.algonquincollege.todo.configuration;

import com.algonquincollege.todo.model.User;
import com.algonquincollege.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The Class CustomUserDetailsService is responsible for locating users using the UserRepository in order to create Spring Security "Users" using a username, password, and role.
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Locates a stored user using a username and creates a Spring Security "User".
     * @param username
     *                  the username used to locate a stored user.
     * @return the User which implements the UserDetails interface
     * @throws UsernameNotFoundException if the user does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    /**
     * Parses the list of strings containing a Users' roles.
     * @param user
     *              the User model object containing roles
     * @return the String array containing the Users' roles
     */
    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
