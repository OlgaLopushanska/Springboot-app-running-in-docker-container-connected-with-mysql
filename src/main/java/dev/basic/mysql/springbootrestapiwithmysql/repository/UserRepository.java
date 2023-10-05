package dev.basic.mysql.springbootrestapiwithmysql.repository;

import dev.basic.mysql.springbootrestapiwithmysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
