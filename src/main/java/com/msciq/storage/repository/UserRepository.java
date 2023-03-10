package com.msciq.storage.repository;

import com.msciq.storage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);

    User findByEmail(String email);

    Optional<User> findById(Long id);

}
