package com.spr.demo.repository;

import com.spr.demo.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>,CrudRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
    ApplicationUser findApplicationUserById(Long id);
}
