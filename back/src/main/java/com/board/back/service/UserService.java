//package com.board.back.service;
//
//import com.board.back.domain.UserEntity;
//import com.board.back.dto.UserDto;
//import com.board.back.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    @Transactional
//    public void signUpUser(UserDto userForm) throws Exception {
//        validateDuplicateUserId(userForm.getUserId());
//        userRepository.save(UserEntity.createUser(userForm));
//    }
//
//
//    private void validateDuplicateUserId(String userId) throws Exception {
//        if (userRepository.existsByUserId(userId)){
//            throw new Exception();
//        }
//
//    }
//
//}
