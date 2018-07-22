package com.spr.demo.service;

import com.spr.demo.entity.ApplicationUser;
import com.spr.demo.exception.UserNotFoundException;
import com.spr.demo.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    public ApplicationUser getUserByUserId(Long userId) throws UserNotFoundException {
        ApplicationUser retrievedApplicationUser = applicationUserRepository.findApplicationUserById(userId);
        if(retrievedApplicationUser == null){
            throw new UserNotFoundException("User not found with the given id: " + userId);
        }
        return retrievedApplicationUser;
    }

    public ApplicationUser addUser(ApplicationUser user){
        return applicationUserRepository.save(user);
    }

    public ApplicationUser updateUser(ApplicationUser user) throws UserNotFoundException{
        ApplicationUser retrievedApplicationUser = applicationUserRepository.findApplicationUserById(user.getId());
        if(retrievedApplicationUser == null){
            throw new UserNotFoundException("User not found with the id: " + user.getId());
        }

        return applicationUserRepository.save(user);
    }

    public void deleteUser(Long userId) throws UserNotFoundException{
        ApplicationUser retrievedUser = applicationUserRepository.findApplicationUserById(userId);
        if(retrievedUser == null){
            throw new UserNotFoundException("User not found with the given id: " + userId);
        }
        deleteUser(userId);
    }

}