package com.bestudios.kampusellapi.security.services;

import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
  @Autowired
  StudentRepository studentRepository;
 
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
    Student user = studentRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
 
    return UserPrinciple.build(user);
  }
}