package com.board.back.service;

import com.board.back.dto.UserDto;
import com.board.back.entity.Board;
import com.board.back.entity.Users;
import com.board.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();

        Users usersEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        if (usersEntity.getUserId().equals(userId)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(usersEntity.getUserId(), usersEntity.getPassword(), authorities);
    }

    @Transactional
    public void signUp(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users users = Users.builder()
                .userId(userDto.getUserId())
                .userNm(userDto.getUserNm())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userRepository.save(users);
    }

    public boolean validateDuplicateUsers(UserDto userDto) {
        return userRepository.findByUserId(userDto.getUserId()).isEmpty(); //true=회원가입가능
    }

}
