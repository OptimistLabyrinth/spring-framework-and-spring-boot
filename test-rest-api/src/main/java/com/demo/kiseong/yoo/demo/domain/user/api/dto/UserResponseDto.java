package com.demo.kiseong.yoo.demo.domain.user.api.dto;

import com.demo.kiseong.yoo.demo.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class UserResponseDto {

    @Getter
    @RequiredArgsConstructor
    public static class Basic {
        private final Long id;
        private final String firstName;
        private final String lastName;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public Basic(User user) {
            id = user.getId();
            firstName = user.getFirstName();
            lastName = user.getLastName();
            email = user.getEmail();
            createdAt = user.getCreatedAt();
            updatedAt = user.getUpdatedAt();
        }
    }

}
