package ru.kpfu.bookstore.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.bookstore.Repositories.UserRepository;
import ru.kpfu.bookstore.entities.User;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;


@Controller
@SessionAttributes("user")
public class UserRegistrationController {

    @Autowired
    private UserRepository repository;

    @RequestMapping( value = "/user_regist", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
       modelMap.addAttribute("modal_visible",false);
        return "index";
    }



    @RequestMapping("/reg")
    public String register(ModelMap map) {
        map.addAttribute("user", new User());
        //map.addAttribute("message", "123");
        map.addAttribute("modal_visible",false);
        return "register";
    }
    @RequestMapping( value = "/user_regist", method = RequestMethod.POST)
    public String registerHandler(
            ModelMap map,
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute("user") User user,
            BindingResult result



    ) {
        if (result.hasErrors()) {
            map.addAttribute("modal_visible", true);

            map.addAttribute("login_or_regist", "cd-signup");
            map.addAttribute("login_or_regist1", 1);
            return "index";

//
//                map.addAttribute("login_or_regist","cd-login");
//
//                map.addAttribute("login_or_regist1",0);
//                return "index";

        } else {

            map.addAttribute("modal_visible",false);
            repository.save(user);

            redirectAttributes.addFlashAttribute("message", "User " + user.getName() + " was successfully added!");
           // redirectAttributes.addFlashAttribute("userList", repository.findAll());

            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("URC#register").build();
        }
    }

}



