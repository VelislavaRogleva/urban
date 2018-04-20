package app.urbanist;

import app.urbanist.entity.Role;
import app.urbanist.entity.User;
import app.urbanist.exception.EmailNotUniqueException;
import app.urbanist.exception.UsernameNotUniqueException;
import app.urbanist.model.binding.UserRegisterModel;
import app.urbanist.repository.RoleRepository;
import app.urbanist.repository.UserRepository;
import app.urbanist.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceRegisterTests {

    private static final String PASSWORD_HASH = "hashedPassword";
    private static final String INVALID_USERNAME = "invalidUsername";
    private static final String INVALID_EMAIL = "invalidEmail";

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRegisterModel userRegisterModel;
    private Set<Role> roles;


    @Before
    public void setUp() {
        Role role = new Role();
        role.setName("USER");

        this.roles = new HashSet<>();
        this.roles.add(role);

        User user = new User();

        when(this.userRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(this.userRepository.findByUsername(INVALID_USERNAME)).thenReturn(user);
        when(this.userRepository.findByEmail(INVALID_EMAIL)).thenReturn(user);
        when(this.roleRepository.findByName("USER")).thenReturn(role);
        when(this.passwordEncoder.encode(anyString())).thenReturn(PASSWORD_HASH);

        this.userRegisterModel = new UserRegisterModel();
        this.userRegisterModel.setUsername("username");
        this.userRegisterModel.setPassword("password");
        this.userRegisterModel.setEmail("email");

    }

    @Test
    public void testRegisterUser_withValidUser_shouldMapCorrectly() throws EmailNotUniqueException, UsernameNotUniqueException {

        User user = this.userService.registerUser(this.userRegisterModel);

        assertEquals("Username wasn't mapped correctly", this.userRegisterModel.getUsername(), user.getUsername());
        assertEquals("Email wasn't mapped correctly", this.userRegisterModel.getEmail(), user.getEmail());
    }

    @Test
    public void testRegisterUser_withValidUser_shouldReturnHashedPassword() throws EmailNotUniqueException, UsernameNotUniqueException {
        User user = this.userService.registerUser(this.userRegisterModel);

        assertEquals("Password wasn't hashed correctly", PASSWORD_HASH, user.getPassword());
    }

    @Test(expected = UsernameNotUniqueException.class)
    public void testRegisterUser_withInvalidUsername_shouldThrowException() throws EmailNotUniqueException, UsernameNotUniqueException {
        UserRegisterModel urm = new UserRegisterModel();
        urm.setUsername(INVALID_USERNAME);

        this.userService.registerUser(urm);
    }

    @Test(expected = EmailNotUniqueException.class)
    public void testRegisterUser_withInvalidEmail_shouldThrowException() throws EmailNotUniqueException, UsernameNotUniqueException {
        UserRegisterModel urm = new UserRegisterModel();
        urm.setEmail(INVALID_EMAIL);

        this.userService.registerUser(urm);
    }


    @Test
    public void testRegisterUser_withValidUser_shouldHaveUserRole() throws EmailNotUniqueException, UsernameNotUniqueException {
        User user = this.userService.registerUser(this.userRegisterModel);

        assertEquals("User should have USER role", this.roles, user.getRoles());
    }

}
