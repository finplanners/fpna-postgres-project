package com.msciq.storage.repository;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findByStatus(String status);

    List<User> findByIdIn(List<Long> ids);

    User findByEmailAndStatus(String email, String active);

    List<User> findByStatusAndIsDeleted(String status, boolean b);
}
