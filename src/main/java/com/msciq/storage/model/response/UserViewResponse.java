package com.msciq.storage.model.response;

import com.msciq.storage.model.Role;
import com.msciq.storage.model.User;
import lombok.*;

import java.util.List;
import java.util.Set;

@ToString
@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class UserViewResponse extends User {
    private Set<String> userRoles;

    private List<Role> roles;

}
