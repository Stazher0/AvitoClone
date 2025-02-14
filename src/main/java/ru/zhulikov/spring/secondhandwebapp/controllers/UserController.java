package ru.zhulikov.spring.secondhandwebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zhulikov.spring.secondhandwebapp.models.Users;
import ru.zhulikov.spring.secondhandwebapp.repositories.UserRepository;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{user_id}")
    public String userProfile(@PathVariable(value = "user_id") long user_id, Model model) {
        // Проверка существования пользователя
        Optional<Users> optionalUser = userRepository.findById(user_id);
        if (optionalUser.isEmpty()) {
            return "redirect:/"; // Перенаправляем, если пользователь не найден
        }

        Optional<Users> users = userRepository.findById(user_id);
        users.ifPresent(value -> model.addAttribute("users",value));



        return "user_profile.html";

    }
}
