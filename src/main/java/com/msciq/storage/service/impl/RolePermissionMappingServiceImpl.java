package com.msciq.storage.service.impl;

import com.msciq.storage.model.Role;
import com.msciq.storage.model.RolePermissionMapping;
import com.msciq.storage.model.response.RolePermissionViewResponse;
import com.msciq.storage.repository.PermissionRepository;
import com.msciq.storage.repository.RolePermissionMappingRepository;
import com.msciq.storage.repository.RoleRepository;
import com.msciq.storage.security.Actions;
import com.msciq.storage.service.RolePermissionMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RolePermissionMappingServiceImpl implements RolePermissionMappingService {
    @Autowired
    private RolePermissionMappingRepository rolePermissionMappingRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<RolePermissionMapping> saveAllRolePermissionMappings(List<RolePermissionMapping> rolePermissionMappingList) {
        List<RolePermissionMapping> rolePermissionMappings = rolePermissionMappingRepository.saveAll(rolePermissionMappingList);
       return rolePermissionMappings;
    }

    @Override
    public List<RolePermissionMapping> getRolePermissionMappings() {
            return rolePermissionMappingRepository.findAll();
    }
    public Map<String, Set<Actions>> userClaimData(String roleName){
        Map<String, Set<Actions>> permissionObject = new HashMap<>();

        try{
            log.info(" user claim service start "+roleName);
            List<RolePermissionMapping> rolePermissionMappings = rolePermissionMappingRepository.getAllRolePermissionMappingByRoleName(roleName,true);
            log.info("role permission object size "+String.valueOf(rolePermissionMappings.size()));
            for (RolePermissionMapping rolePermissionMapping : rolePermissionMappings) {
                Set<Actions> actions = new HashSet<>();
                Map<String,Object> actionControlData = new HashMap<>();
                if(rolePermissionMapping.isCreate())
                    actions.add(Actions.CREATE);
                if(rolePermissionMapping.isRead())
                    actions.add(Actions.READ);
                if(rolePermissionMapping.isUpdate())
                    actions.add(Actions.UPDATE);
                if(rolePermissionMapping.isDelete())
                    actions.add(Actions.DELETE);


                permissionObject.put(rolePermissionMapping.getPermissionObject(),actions);
            }
             return permissionObject;
        }catch (Exception e){
            log.error(e.getMessage());
            return permissionObject;
        }
    }
    public List<RolePermissionViewResponse> getAllRolePermission(boolean status){
        List<Role> roles = roleRepository.getAllRoles(status);
        List<RolePermissionViewResponse> rolePermissionViewResponses = new ArrayList<>();
        for(Role role : roles){
            RolePermissionViewResponse rolePermissionViewResponse = new RolePermissionViewResponse();
            List<RolePermissionMapping> rolePermissionMappings = rolePermissionMappingRepository.getAllRolePermissionMappingByRoleName(role.getName(),status);
            rolePermissionViewResponse.setRolePermission(rolePermissionMappings);
            rolePermissionViewResponse.setName(role.getName());
            rolePermissionViewResponse.setDescription(role.getDescription());
            rolePermissionViewResponses.add(rolePermissionViewResponse);

        }
        return rolePermissionViewResponses;
    }
}
