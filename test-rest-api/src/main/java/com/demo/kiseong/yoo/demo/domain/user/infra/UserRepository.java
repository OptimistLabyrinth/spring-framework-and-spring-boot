package com.demo.kiseong.yoo.demo.domain.user.infra;

import com.demo.kiseong.yoo.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
