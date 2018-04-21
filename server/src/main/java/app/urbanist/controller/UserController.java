package app.urbanist.controller;

import app.urbanist.entity.User;
import app.urbanist.exception.EmailNotUniqueException;
import app.urbanist.exception.UsernameNotUniqueException;
import app.urbanist.model.binding.UserEditModel;
import app.urbanist.model.binding.UserRegisterModel;
import app.urbanist.model.view.UserViewModel;
import app.urbanist.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<UserViewModel> allUsers = this.userService.getAllUsers();

        return allUsers;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        UserViewModel userViewModel = this.userService.getUserViewModel(id);

        if (userViewModel == null) return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userViewModel, HttpStatus.OK);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editUser(@RequestBody UserEditModel userEditModel) {
        this.userService.editUser(userEditModel);
    }

    @PostMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deactivateAccount(@PathVariable("id") Long id) {
        this.userService.changeActivated(id, true);
    }

    @PostMapping("/activate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void activateAccount(@PathVariable("id") Long id) {
        this.userService.changeActivated(id, false);
    }
}
