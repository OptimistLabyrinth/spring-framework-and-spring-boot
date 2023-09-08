package com.demo.kiseong.yoo.demo.domain.user.api.dto;

import com.demo.kiseong.yoo.demo.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateDto {
        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;
        @NotEmpty
        @Email
        private String email;

        public User toEntity() {
            return new User(firstName, lastName, email);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateDto {
        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;
        @NotEmpty
        @Email
        private String email;

        public User toEntity(Long id) {
            return new User(id, firstName, lastName, email);
        }
    }

}
