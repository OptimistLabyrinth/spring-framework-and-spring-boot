package com.demo.kiseong.yoo.demo.domain.user.api;

import com.demo.kiseong.yoo.demo.domain.user.api.dto.UserRequestDto;
import com.demo.kiseong.yoo.demo.domain.user.api.dto.UserResponseDto;
import com.demo.kiseong.yoo.demo.domain.user.core.UserService;
import com.demo.kiseong.yoo.demo.domain.user.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto.Basic> createUser(@RequestBody @Valid UserRequestDto.CreateDto createDto) {
        User user = userService.create(createDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto.Basic(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto.Basic> findUser(@PathVariable @Valid @NotNull Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserResponseDto.Basic(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto.Basic>> findAllUsers() {
        List<UserResponseDto.Basic> users = userService.findAll()
            .stream()
            .map(UserResponseDto.Basic::new)
            .toList();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDto.Basic> updateUser(
        @PathVariable @Valid @NotNull Long id,
        @RequestBody @Valid UserRequestDto.UpdateDto updateDto) {
        User user = userService.update(updateDto.toEntity(id));
        return ResponseEntity.ok(new UserResponseDto.Basic(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserResponseDto.Basic> deleteUser(@PathVariable @Valid @NotNull Long id) {
        User user = userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto.Basic(user));
    }

}
