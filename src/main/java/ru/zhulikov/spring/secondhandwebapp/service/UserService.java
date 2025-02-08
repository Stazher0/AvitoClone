package ru.zhulikov.spring.secondhandwebapp.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zhulikov.spring.secondhandwebapp.models.Role;
import ru.zhulikov.spring.secondhandwebapp.models.Users;
import ru.zhulikov.spring.secondhandwebapp.repositories.RoleRepository;
import ru.zhulikov.spring.secondhandwebapp.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Searching for user: " + username); // Логирование поиска пользователя

        Users user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            System.out.println("Found user: " + username);
        }

        System.out.println("Found user: " + user.getUsername() + ", password: " + user.getPassword());

        // Получаем имя роли пользователя
        String role = user.getRole().getRoleName(); // Поскольку у нас только одна роль

        // Возвращаем пользователя с его ролью
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role)
                .build();
    }

    public Users findUserById(Long user_Id) {
        Optional<Users> userFromDb = userRepository.findById(user_Id);
        return userFromDb.orElse(new Users());
    }

    public List<Users> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(Users users) {
        Users userFromDB = userRepository.findByUsername(users.getUsername());

        if (userFromDB != null) {
            return false; // Пользователь уже существует
        }

        // Загрузите роль из базы данных
        Role role = roleRepository.findById(1L).orElse(null); // Предполагаем, что роль с ID 1 - это ROLE_USER
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        users.setRole(role); // Установите существующую роль
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        userRepository.save(users); // Теперь сохраните пользователя
        return true;
    }

    public boolean deleteUser(Long user_Id) {
        if (userRepository.findById(user_Id).isPresent()) {
            userRepository.deleteById(user_Id);
            return true;
        }
        return false;
    }

    public List<Users> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM Users u WHERE u.id > :paramId", Users.class)
                .setParameter("paramId", idMin).getResultList();
    }

    /*public UserDetails getCurrentUser() {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return  userRepository.findByUsername(name);
    }*/
}
