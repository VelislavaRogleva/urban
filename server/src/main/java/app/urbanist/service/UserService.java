package app.urbanist.service;

import app.urbanist.exception.EmailNotUniqueException;
import app.urbanist.exception.UsernameNotUniqueException;
import app.urbanist.model.binding.UserRegisterModel;
import app.urbanist.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    boolean registerUser(UserRegisterModel urm) throws EmailNotUniqueException, UsernameNotUniqueException;

    List<UserViewModel> getAllUsers();
}
