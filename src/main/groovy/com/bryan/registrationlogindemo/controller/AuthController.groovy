package com.bryan.registrationlogindemo.controller

import com.bryan.registrationlogindemo.dto.UserDto
import com.bryan.registrationlogindemo.entity.User
import com.bryan.registrationlogindemo.service.UserService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class AuthController {

    UserService userService

    AuthController(UserService userService) {
        this.userService = userService
    }


    // handler method to handle home page request
    @GetMapping("/index")
    String home() {
        return "index"
    }

    // handler method to handle login request
    @GetMapping("/login")
    String login() {
        return "login"
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto()
        model.addAttribute("user", user)
        return "register"
    }


    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    String registration(@Valid @ModelAttribute("user") UserDto userDto,
                        BindingResult result,
                        Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail())

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register"
        }

        userService.saveUser(userDto);
        return "redirect:/register?success"
    }

    // handler method to handle list of users
    @GetMapping("/users")
    String users(Model model) {
        List<UserDto> users = userService.findAllUsers()
        model.addAttribute("users", users)
        return "users"
    }
}
