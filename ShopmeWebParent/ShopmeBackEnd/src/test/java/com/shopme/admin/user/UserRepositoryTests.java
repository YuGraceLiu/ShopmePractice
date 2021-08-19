package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserWithOneRole() {
        Role admin = entityManager.find(Role.class, 1);
        User John = new User("johnNew@codejava.net", "passw", "john", "Smart");
        John.addRole(admin);
        User savedUser = repo.save(John);
        assertThat(savedUser.getInfoId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithTwoRoles() {
        User Tom = new User("tom@codejava.net", "passw", "Tom", "Smart");
        Tom.addRole(new Role(3));
        Tom.addRole(new Role(2));
        User savedUser = repo.save(Tom);
        assertThat(savedUser.getInfoId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testUsersById() {
        // find by userID;
        User user = repo.findById(6).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User user = repo.findById(6).get();
        user.setEnabled(true);
        user.setEmail("newEmail@email");
        repo.save(user);
    }

    @Test
    public void testSetRolesOfUser(){
        User user = repo.findById(1).get();
        user.setEnabled(true);
        Set<Role> set = new HashSet<>();
        set.add(new Role(4));
        set.add(new Role(5));
        user.setRoles(set);
    }

    @Test
    public void testUpdateRoleOfUser(){
        User user = repo.findById(1).get();
        user.setEnabled(true);
        user.getRoles().remove(new Role(5));
        user.addRole(new Role(2));
        repo.save(user);
    }

    @Test
    public void testDeleteUser(){
        Integer userId = 1;
        repo.deleteById(userId);
    }

}
