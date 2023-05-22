package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Models.UserInfo;
import com.example.LibraryManagementSystem.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserInfoRepository userInfoRepository;
    public String signUp(UserInfo userInfo){
        userInfoRepository.save(userInfo);
        return "user added successfully";
    }

    public UserInfo login(String  name, String password) {
        UserInfo user=userInfoRepository.findByName(name);
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // Login successful
            System.out.println(password+"    "+user.getPassword());
            return user;
        } else {
            // Login failed
            return null;
        }
        //return new UserInfo();
    }
}
