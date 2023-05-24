package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public String UserList(ModelMap model){
        model.addAttribute("users", userService.listUsers());
        return "userList";
    }

    @GetMapping(value = "/addUser")
    public String newUser(ModelMap model) {
        model.addAttribute("newUser", new User());
        return "addUser";
    }
    @PostMapping (value = "/addUser")
    public String create(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String showUser(ModelMap model, @PathVariable("id") long id){
        model.addAttribute("theUser", userService.findUser(id));
        return "showUser";
    }


    @GetMapping("/users/{id}/edit")
    public String showUserUpdatePage(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("theUser", userService.findUser(id));
        return "editUser";
    }


    @PostMapping(value = "/users/{id}/edit")
    public String edit(@ModelAttribute("theUser") User user) {
        userService.updeteUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUserFromList(ModelMap model, @PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
