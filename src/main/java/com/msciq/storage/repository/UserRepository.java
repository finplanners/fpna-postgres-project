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

    public static final String GET_ALL_USER_BY_STATUS= "select u from USER_TBL as u where u.isDeleted=:isDeleted";
    User findByEmail(String email);

    Optional<User> findById(Long id);

    @Query(value = GET_ALL_USER_BY_STATUS)
    List<User> findByUserStatus(@Param("isDeleted") boolean isDeleted);

    List<User> findByIdIn(List<Long> ids);

    User findByEmailAndStatus(String email, String active);
}
