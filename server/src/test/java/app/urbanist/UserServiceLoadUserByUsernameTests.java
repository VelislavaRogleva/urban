package app.urbanist;

import app.urbanist.entity.Role;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceLoadUserByUsernameTests {

    private static final String USERNAME = "username";
    private static final String INVALID_USERNAME = "invalidUsername";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    Set<Role> roles = new HashSet<>();

    @Before
    public void setUp() {

        Role role = new Role();
        role.setName("USER");
        this.roles.add(role);

        this.user = new User();
        user.setUsername(USERNAME);
        user.setPassword("password");
        user.setRoles(this.roles);

        when(this.userRepository.findByUsername(USERNAME)).thenReturn(user);
        when(this.userRepository.findByUsername(INVALID_USERNAME)).thenReturn(null);
    }

    @Test
    public void testFindUserByUsername_withValidUsername_shouldReturnUserDetails() {
        UserDetails userDetails = this.userService.loadUserByUsername(USERNAME);

        String expectedRoles = String.join(",", this.roles.stream().map(x -> "ROLE_" + x.getName()).collect(Collectors.toList()));
        String actualRoles = String.join(",", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        assertEquals("Username was not mapped correctly", user.getUsername(), userDetails.getUsername());
        assertEquals("Authorities were not mapped correctlry", expectedRoles, actualRoles);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testFindUserByUsername_withInvalidUser_shouldThrowException() {
        UserDetails userDetails = this.userService.loadUserByUsername(INVALID_USERNAME);
    }
}
