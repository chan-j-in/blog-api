package com.blog.controller;

import com.blog.dto.UserFormDto;
import com.blog.entity.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(@RequestBody UserFormDto userFormDto) {
        userService.join(userFormDto);
        return "회원 가입이 완료되었습니다.";
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable("id") Long id) {
        return userService.detail(id);
    }

    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserFormDto userFormDto) {
        return userService.update(id, userFormDto);
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id: " + id;
    }
}
