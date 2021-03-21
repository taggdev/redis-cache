package com.tagg.rediscache.repository;

import com.tagg.rediscache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
