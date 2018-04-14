package app.urbanist.service;

import app.urbanist.model.binding.UserRegisterModel;
import app.urbanist.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterModel urm);

    List<UserViewModel> getAllUsers();
}
