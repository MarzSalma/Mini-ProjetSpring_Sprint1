package com.salma.joueurs.services;
import com.salma.joueurs.entities.Role;
import com.salma.joueurs.entities.User;
import com.salma.joueurs.repos.RoleRepository;
import com.salma.joueurs.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRep;
    @Autowired
    RoleRepository roleRep;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }
    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRep.findByUsername(username);
        Role r = roleRep.findByRole(rolename);
        usr.getRoles().add(r);
        return usr;
    }
    @Override
    public void deleteAllusers() {
        userRep.deleteAll();
    }
    @Override
    public void deleteAllRoles() {
        roleRep.deleteAll();
    }
    @Override
    public Role addRole(Role role) {
        return roleRep.save(role);
    }
    @Override
    public User findUserByUsername(String username) {
        return userRep.findByUsername(username);
    }
}
