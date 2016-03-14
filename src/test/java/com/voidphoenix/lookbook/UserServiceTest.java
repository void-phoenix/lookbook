package com.voidphoenix.lookbook;

import com.voidphoenix.lookbook.service.UserService;
import com.voidphoenix.lookbook.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LookbookApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGettingAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
    }

}
