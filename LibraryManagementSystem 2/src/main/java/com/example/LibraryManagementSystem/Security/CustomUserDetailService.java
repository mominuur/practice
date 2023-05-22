package com.example.LibraryManagementSystem.Security;

import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Models.UserInfo;
import com.example.LibraryManagementSystem.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Optional<UserInfo> userInfo = userInfoRepository.findByName(userName);
        Optional<UserInfo> userInfo = userInfoRepository.findAllByName(userName);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + userName));

    }
}
