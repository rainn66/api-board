//package com.board.back.domain;
//
//import com.board.back.dto.UserDto;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Table(name="user")
//public class UserEntity {
//
//    @Id
//    private String userId;
//
//    private String userNm;
//
//    private String userPassword;
//
//    private LocalDateTime regDt;
//
//    public User() {}
//
//    public User(UserDto userForm) {
//        this.userId = userForm.getUserId();
//        this.userNm = userForm.getUserNm();
//        this.userPassword = userForm.getUserPassword();
//        this.regDt = LocalDateTime.now();
//    }
//
//    public static UserEntity createUser(UserDto userForm) {
//        return new UserEntity(userForm);
//    }
//
//}
