package ru.zhulikov.spring.secondhandwebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhulikov.spring.secondhandwebapp.models.Advt;
import ru.zhulikov.spring.secondhandwebapp.models.Users;
import ru.zhulikov.spring.secondhandwebapp.repositories.AdvtRepository;
import ru.zhulikov.spring.secondhandwebapp.service.UserService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AdvtController {

    @Autowired
    private AdvtRepository advtRepository;

    //вывод объявлений и главная страница
    @GetMapping("/")
    public String advtMain(Model model) {

        Iterable<Advt> advertisments = advtRepository.findAll();
        model.addAttribute("advertisments", advertisments);

        return "index";
    }
    //вывод объявлений и главная страница KONEC

//добавить объявление

@GetMapping("/add")
public String advtAdd(Model model) { return "advt_add"; }

@PostMapping("/advt/add")
public String advtAddPost(@RequestParam String name, int price, String description, String imageName, Model model){

    Advt advt = new Advt(name, price, description, imageName );
    advtRepository.save(advt);

    return "redirect:/";
}
//добавить объявление конец

    @GetMapping("/advt/{id}")
    public String advtInfo(@PathVariable(value = "id") long id, Model model) {
        // если такого id не существует
        if (!advtRepository.existsById(id)){
            return "redirect:/";
        }

        Optional<Advt> advt = advtRepository.findById(id);
        advt.ifPresent(value -> model.addAttribute("advt", value));

        System.out.println(advt);

        return "advt_info"; // Возвращаем имя шаблона
    }


}
