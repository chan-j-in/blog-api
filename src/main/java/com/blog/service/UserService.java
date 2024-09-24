package com.blog.service;

import com.blog.dto.UserFormDto;
import com.blog.entity.User;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void join(UserFormDto userFormDto) {
        User user = new User(userFormDto.getUsername(), userFormDto.getPassword(), userFormDto.getEmail());
        userRepository.save(user);
    }

    public User detail(Long id){
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 user는 없습니다. id: " + id);
            }
        });
        return user;
    }

    public User update(Long id, UserFormDto userFormDto) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        if (!userFormDto.getPassword().isEmpty())
            user.updatePassword(userFormDto.getPassword());
        if (!userFormDto.getEmail().isEmpty())
            user.updateEmail(userFormDto.getEmail());
        return user;
    }
}
