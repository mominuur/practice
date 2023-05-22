package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.UserInfo;
import com.example.LibraryManagementSystem.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @PostMapping("/add")
    public String addAdmin(@RequestBody()UserInfo userInfo){
        return userInfoService.signUp(userInfo);
    }
    @GetMapping("/getusers")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<UserInfo> getUser(@RequestParam("user")String user, @RequestParam("password") String  password){
        UserInfo userInfo = userInfoService.login(user,password);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }
}
