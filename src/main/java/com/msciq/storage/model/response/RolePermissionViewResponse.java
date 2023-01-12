package com.msciq.storage.model.response;

import com.msciq.storage.model.Role;
import com.msciq.storage.model.RolePermissionMapping;
import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class RolePermissionViewResponse extends  Role {


        private List<RolePermissionMapping> rolePermission;

}
