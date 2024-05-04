package com.utfpr.todo.spring.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.utfpr.todo.clean.infra.model.UserModel;
import com.utfpr.todo.clean.infra.repository.UserModelJpaRepository;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserModelJpaRepository userRepository;

    @Test
    public void createUser_WithDataValid_ReturnsUser() {

        UserModel createdUser = userRepository.save(UserConstants.CREATED_USER);

        System.out.println("createdUser");
        System.out.println(createdUser);

        UserModel foundUser = userRepository.findById(createdUser.getId()).get();

        System.out.println("foundUser");
        System.out.println(foundUser);

        Assertions.assertThat(createdUser).isEqualTo(foundUser);

    }

    @Test
    public void createUser_WithInvalidData_ThrowsException() {

        UserModel emptyUser = UserConstants.USER_EMPTY;

        Assertions.assertThatThrownBy(() -> userRepository.save(emptyUser)).isInstanceOf(RuntimeException.class);

    }
}
