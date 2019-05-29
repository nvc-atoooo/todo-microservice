package com.example.authservice.service;

import com.example.authservice.controller.form.UserForm;
import com.example.authservice.domain.Authority;
import com.example.authservice.domain.User;
import com.example.authservice.domain.UserAuthority;
import com.example.authservice.domain.UserAuthorityPrimaryKey;
import com.example.authservice.repository.AuthorityRepository;
import com.example.authservice.repository.UserAuthorityRepository;
import com.example.authservice.repository.UserRepository;
import com.example.lib.exception.ResourceViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(UserForm userForm) throws ResourceViolationException {
        if(userRepository.existsByUsername(userForm.getUsername())) {
            throw new ResourceViolationException("User exist");
        }

        User user = new User(userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()), false);
        userRepository.save(user);

        Authority authority = authorityRepository.findByAuthority(Authority.ROLE_USER);
        UserAuthorityPrimaryKey id = new UserAuthorityPrimaryKey(user.getUser_id(), authority.getId());

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserAuthorityPrimaryKey(id);
        userAuthorityRepository.save(userAuthority);

        return user;
    }

    @Override
    public void activateUser(String email) throws ResourceViolationException {
        User user = getByUsername(email);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) throws ResourceViolationException {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new ResourceViolationException("Email not exist");
        }

        return user;
    }
}
