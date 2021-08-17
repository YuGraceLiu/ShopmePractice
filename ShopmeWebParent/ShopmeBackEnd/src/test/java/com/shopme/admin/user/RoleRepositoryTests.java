package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateFirstRole(){
        Role roleAdmin = new Role("Admin", "Manage everything");
        Role savedRole = repo.save(roleAdmin); // push into the data base
        assertThat(savedRole.getId()).isGreaterThan(0); // if true: the savedRole put into the database successfully
    }
}
