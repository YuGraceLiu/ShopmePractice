package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private RoleRepository RoleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private void encodePassword(User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
    }

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) RoleRepo.findAll();
    }

    public void save(User user) {
        encodePassword(user);
        repo.save(user);
    }
}
