package com.salma.joueurs.services;

import com.salma.joueurs.entities.Role;
import com.salma.joueurs.entities.User;

public interface UserService {
    void deleteAllusers();
    void deleteAllRoles();
    User saveUser(User user);
    User findUserByUsername (String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
}

