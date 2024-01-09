package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserUserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("not user");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),mapRoles(user.getRoles()));
    }
    private List<? extends GrantedAuthority> mapRoles(List<Role>roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void addUser(User newUser) {

    }


    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

//    @Override
//    public void editUser(long userId, User updatedUser, List<Long> roleIds) {
//userRepository.save(updatedUser);
//    }
    @Override
    public void editUser(User updatedUser) {
        userRepository.save(updatedUser);
    }
    @Override
    public User getUser(long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        return foundUser.orElse(null);
    }

}
