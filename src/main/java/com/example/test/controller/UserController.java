package com.example.test.controller;


import com.example.test.dto.UserRegisterForm;
import com.example.test.entity.User;
import com.example.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/main")
    public String showMainPage(Principal principal, Model model){
        User user = userService.getUser(principal.getName());
        model.addAttribute("user",user);
        return "main";
    }

    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new UserRegisterForm());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid UserRegisterForm customerRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes) {
        attributes.addFlashAttribute("dto", customerRequestDto);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:register";
        }

        userService.register(customerRequestDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        if (error) {
            return "redirect";
        }
        return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @PostMapping("/delete")
    public String deleteUserByEmail(String email){
        userService.deleteUser(email);
       return "redirect:/main";
    }
    @PostMapping("/addAdmin")
    public String addUserAdmin(String email){
        userService.addAdmin(email);
       return "redirect:/main";
    }

}
