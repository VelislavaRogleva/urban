package app.urbanist.controller;

import app.urbanist.exception.EmailNotUniqueException;
import app.urbanist.exception.UsernameNotUniqueException;
import app.urbanist.model.binding.UserRegisterModel;
import app.urbanist.model.view.UserViewModel;
import app.urbanist.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegisterModel urm) throws EmailNotUniqueException, UsernameNotUniqueException {
        this.userService.registerUser(urm);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserViewModel> getAllUsers() {
        return this.userService.getAllUsers();
    }
}
