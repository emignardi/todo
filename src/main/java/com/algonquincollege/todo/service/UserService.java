package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.User;
import com.algonquincollege.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class UserService handles the creation/reading/updates/deletion of users, used for authentication.
 * <br>
 * This class is the Service layer containing the business logic for a multi-tiered architecture.
 * @author Eric Mignardi
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Finds a single user that exists in the database.
     * @param id
     *              the ID used to locate a stored user.
     * @return a single user
     * @throws IllegalArgumentException if the user does not exist
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Finds all available users that exist in the database.
     * @return a list of created users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Saves a user into the database using a User model object.
     * @param user
     *              the User model object used to create a new user
     * @return the new user that was saved to the database
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Locates a user from the database by ID and updates that user with an updated User model object.
     * @param id
     *              the ID used to locate a stored user
     * @param user
     *              the updated User model object used to modify a user
     * @return the updated user that was saved to the database
     * @throws IllegalArgumentException if the user does not exist
     */
    public User update(Long id, User user) {
        User updatedUser = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        userRepository.save(updatedUser);
        return updatedUser;
    }

    /**
     * Deletes a user from the database by ID.
     * @param id
     *              the ID used to locate a stored user
     * @return the user that was deleted from the database
     * @throws IllegalArgumentException if the user does not exist
     */
    public User delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
        return user;
    }

    /**
     * Locates a user in the database by username.
     * @param username
     *                  the username to locate a stored user (necessary for Spring Security)
     * @return the user stored in the database
     * @throws IllegalArgumentException if the user does not exist
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
    }

}
