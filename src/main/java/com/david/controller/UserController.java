package com.david.controller;

import com.david.model.Role;
import com.david.model.User;
import com.david.repository.RoleRepository;
import com.david.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by david on 28/04/2017.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(produces = "application/json")
    public User getUser(@RequestParam("id") Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/list", produces = "application/json")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @RequestMapping(value = "/login", produces = "application/json")
    public User login(User user) {
        return user;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json")
    public User signupUser(@RequestBody User user) {

        List<Role> roles = new ArrayList<>();
        Role role = null;
        if(user.isAdministrator()) {
            role = roleRepository.findByName("ADMIN");

        } else {
            role = roleRepository.findByName("USER");
        }
        roles.add(role);
        user.setRoles(roles);

        User userResponse = repository.saveAndFlush(user);
        userResponse.setPassword(null);

        return userResponse;
    }

    @RequestMapping("/remove")
    public String deleteUser(@RequestParam("id") Long id) {
        repository.delete(id);

        return "Success";
    }

}
