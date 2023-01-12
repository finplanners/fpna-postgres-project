package com.msciq.storage.repository;

import com.msciq.storage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //public static final String GET_ALL_ROLES = "select * from User as user where user.id in =:status ";
    User findByEmail(String email);

    Optional<User> findById(Long id);

    //@Query(value = GET_ALL_ROLES)
    //List<User> findAllByIds(List<Long> ids);

    List<User> findByIdIn(List<Long> ids);

}
