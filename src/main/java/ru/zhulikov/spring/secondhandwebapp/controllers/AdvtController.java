package ru.zhulikov.spring.secondhandwebapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhulikov.spring.secondhandwebapp.models.Advt;
import ru.zhulikov.spring.secondhandwebapp.repositories.AdvtRepository;

@Controller
public class AdvtController {

    @Autowired
    AdvtRepository advtRepository;

    @GetMapping("/advt")
    public String advtMain(Model model) {
        return "index";
    }


@GetMapping("/add")
public String advtAdd(Model model) { return "advt_add"; }

@PostMapping("/advt/add")
public String advtAddPost(@RequestParam String name, int price, String description, Model model){
    Advt advt = new Advt(name, price, description);
    advtRepository.save(advt);

    return "redirect:/";
}

}
