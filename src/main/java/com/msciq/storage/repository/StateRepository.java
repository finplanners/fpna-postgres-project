package com.msciq.storage.repository;

import com.msciq.storage.common.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, String> {


    State findByName(String name);
}
