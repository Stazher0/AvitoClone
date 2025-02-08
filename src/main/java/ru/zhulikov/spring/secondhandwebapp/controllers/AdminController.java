package ru.zhulikov.spring.secondhandwebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhulikov.spring.secondhandwebapp.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam (required = true,defaultValue = "") Long userId,
                             @RequestParam (required = true,defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{user_id}")
    public String getUser(@PathVariable("user_id") Long user_id, Model model) {
        model.addAttribute("allUsers", userService.usergtList(user_id));
        return "admin";
    }

}
