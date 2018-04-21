package app.urbanist.service;

import app.urbanist.entity.User;
import app.urbanist.exception.EmailNotUniqueException;
import app.urbanist.exception.UsernameNotUniqueException;
import app.urbanist.model.binding.UserEditModel;
import app.urbanist.model.binding.UserRegisterModel;
import app.urbanist.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    User registerUser(UserRegisterModel urm) throws EmailNotUniqueException, UsernameNotUniqueException;

    List<UserViewModel> getAllUsers();

    User getOne(Long id);

    UserViewModel getUserViewModel(Long id);

    User editUser(UserEditModel userEditModel);

    void changeActivated(Long id, boolean b);
}
