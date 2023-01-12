package com.msciq.storage.common;

public class QueryConstants {
    public static final String GET_PERMISSION_BY_ROLE_NAME= "from RolePermissionMapping rs where rs.roleName =:roleName and rs.isActive =:isActive";

    public static final String GET_USER_ROLES= "from UserRoleMapping urm where urm.userId =:id";


}
