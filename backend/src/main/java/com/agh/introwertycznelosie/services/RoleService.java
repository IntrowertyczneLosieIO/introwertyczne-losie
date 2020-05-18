package com.agh.introwertycznelosie.services;

import com.agh.introwertycznelosie.data.Role;
import com.agh.introwertycznelosie.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    public void createRoles(){
        roleRepository.save(new Role(Role.Roles.ADMIN));
        roleRepository.save(new Role(Role.Roles.SPECIALUSER));
        roleRepository.save(new Role(Role.Roles.USER));
    }

    public Role findbyName(String name) {
        return roleRepository.findByName(name);
    }

}
