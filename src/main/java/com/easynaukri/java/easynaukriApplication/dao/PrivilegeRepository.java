package com.easynaukri.java.easynaukriApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easynaukri.java.easynaukriApplication.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
