package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easynaukri.java.easynaukriApplication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
