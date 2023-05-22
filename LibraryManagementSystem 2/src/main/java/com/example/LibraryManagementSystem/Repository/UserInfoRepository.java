package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByName(String name);

    Optional<UserInfo> findAllByName(String userName);
}
