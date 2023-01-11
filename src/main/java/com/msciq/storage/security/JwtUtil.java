package com.msciq.storage.security;

import com.msciq.storage.model.RolePermissionMapping;
import com.msciq.storage.model.User;
import com.msciq.storage.repository.RolePermissionMappingRepository;
import com.msciq.storage.repository.UserRepository;
import com.msciq.storage.service.UserManagementService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
public class JwtUtil {

    private String secret = "fpna";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    RolePermissionMappingRepository rolePermissionMappingRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractClaims(String token){
        return getAllClaimsFromToken(token);
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("Could not get all claims Token from passed token");
            claims = null;
        }
        return claims;
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        List<String> list = new ArrayList<>();
        User userFromDb = userRepository.findByEmail(email);
        Map<String,Map<String,Object>> claimsData = new HashMap<>();
        Map<String, Object> permissionObject = new HashMap<>();
        List<RolePermissionMapping> rolePermissionMappings =rolePermissionMappingRepository.getAllRolePermissionMappingByRoleName(userFromDb.getUserType());
        for (RolePermissionMapping rolePermissionMapping: rolePermissionMappings) {
            Set<Actions> actions = new HashSet<>();
            Map<String,Object> permission = new HashMap<>();
            if(rolePermissionMapping.isCreate())
                actions.add(Actions.CREATE);
            if(rolePermissionMapping.isRead())
                actions.add(Actions.READ);
            if(rolePermissionMapping.isUpdate())
                actions.add(Actions.UPDATE);
            if(rolePermissionMapping.isDelete())
                actions.add(Actions.DELETE);

            permission.put("controlData",rolePermissionMapping.getControlData());
            permission.put("action",actions);

            permissionObject.put(rolePermissionMapping.getPermissionObject(),permission);
            claimsData.put(rolePermissionMapping.getRoleName(), permissionObject);

        }
        claims.put("user_roles",claimsData);
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

