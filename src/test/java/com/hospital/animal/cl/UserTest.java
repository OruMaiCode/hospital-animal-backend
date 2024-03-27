package com.hospital.animal.cl;

import com.hospital.animal.cl.dto.User;
import com.hospital.animal.cl.services.firebase.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest()

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    @Autowired
    UserServiceImpl userService;
    private static User user;

    private static final String NAME="USER_NAME_EXAMPLE";
    private static final String EMAIL="example@test.cl";
    private static final Integer AGE=10;
    private static final String AVATAR="https://ui-avatars.com/api/?name=USER_NAME_EXAMPLE";
    @BeforeAll
    public static void setup(){
        user = new User();
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setAge(AGE);
        user.setAvatar(AVATAR);
    }
    @Order(1)
    @Test
    void createUser() throws InterruptedException, ExecutionException {
        user = userService.create(user);
    }
    @Order(2)
    @Test
    void getAllUser() throws InterruptedException, ExecutionException {
        List<User> users = userService.getAll();
        Assertions.assertTrue(users.size()>0);
        users.forEach(usr->{
            Assertions.assertEquals(NAME,usr.getName());
            Assertions.assertEquals(EMAIL,usr.getEmail());
            Assertions.assertEquals(AGE,usr.getAge());
            Assertions.assertEquals(AVATAR,usr.getAvatar());
            Assertions.assertNotNull(usr.getUid());
        });
    }
    @Order(3)
    @Test
    void getUser() throws InterruptedException, ExecutionException {
        User user1=userService.getByUid(user.getUid());
        Assertions.assertEquals(NAME,user1.getName());
        Assertions.assertEquals(EMAIL,user1.getEmail());
        Assertions.assertEquals(AGE,user1.getAge());
        Assertions.assertEquals(AVATAR,user1.getAvatar());
        Assertions.assertNotNull(user1.getUid());
    }

    @Order(4)
    @Test
    void editUser()throws InterruptedException, ExecutionException {
        Integer age =user.getAge();
        Integer newAge = age+10;
        user.setAge(newAge);
        userService.update(user);
        User user1 = userService.getByUid(user.getUid());
        Assertions.assertEquals(user1.getAge(),newAge);
        user1.setAge(age);
        userService.update(user1);
        Assertions.assertEquals(user1.getAge(),age);
    }

    @Order(5)
    @Test
    void deleteUser() throws InterruptedException {
        Assertions.assertTrue(userService.delete(user.getUid()));
    }
}
