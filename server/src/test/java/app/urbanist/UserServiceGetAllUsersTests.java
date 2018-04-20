package app.urbanist;

import app.urbanist.entity.User;
import app.urbanist.model.view.UserViewModel;
import app.urbanist.repository.UserRepository;
import app.urbanist.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceGetAllUsersTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private List<User> users;

    @Before
    public void setUp() {
        User user1 = new User();
        user1.setUsername("username1");
        user1.setPassword("password1");
        User user2 = new User();
        user2.setUsername("username2");
        user2.setPassword("password2");

        this.users = new ArrayList<>(){{
            add(user1);
            add(user2);
        }};
        when(this.userRepository.findAll()).thenReturn(this.users);
    }

    @Test
    public void testGetAllUsers_shouldReturnUserViewModels() {

        List<UserViewModel> userModels = this.userService.getAllUsers();

        String expectedUsernames = String.join(",", this.users.stream().map(User::getUsername).collect(Collectors.toList()));
        String actualUsernames = String.join(",", userModels.stream().map(UserViewModel::getUsername).collect(Collectors.toList()));

        String expectedEmails = String.join(",", this.users.stream().map(User::getEmail).collect(Collectors.toList()));
        String actualEmails = String.join(",", userModels.stream().map(UserViewModel::getEmail).collect(Collectors.toList()));

        assertEquals("Usernames weren't mapped correctly", expectedUsernames, actualUsernames);
        assertEquals("Emails weren't mapped correctly", expectedEmails, actualEmails);

    }
}
