package com.msciq.storage.repository;

import com.msciq.storage.model.RolePermissionMapping;
import com.msciq.storage.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.msciq.storage.common.QueryConstants.GET_USER_ROLES;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Long>{

    @Query(countQuery = GET_USER_ROLES)
    public List<UserRoleMapping> getAllRoleByUserId(@Param("userId") Long id);

    UserRoleMapping findByUserIdAndRoleName(Long id, String role);
}
