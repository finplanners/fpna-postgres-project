package com.msciq.storage.security;

import com.msciq.storage.model.UserContextHolder;
import com.msciq.storage.model.UserDTO;
import com.msciq.storage.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService service;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getRequestURI().contains("/fpa/user/sign-up")
                || httpServletRequest.getRequestURI().contains("/fpa/organization/create")
                || httpServletRequest.getRequestURI().contains("/fpa/user/login")){
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {

            String url = httpServletRequest.getRequestURI();
            String methodType = httpServletRequest.getMethod();
            String authorizationHeader = httpServletRequest.getHeader("Authorization");

            String token = null;
            String email = null;
            Claims claims = null;
            Map<String, Map<String, Map<String,Object>>> userRolesClaims = new HashMap<>();

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                email = jwtUtil.extractUsername(token);
                claims = jwtUtil.extractClaims(token);
                logger.info(claims.get("user_roles"));
                userRolesClaims = (Map<String, Map<String, Map<String,Object>>>) claims.get("user_roles");

                List<String> actualClaims=new ArrayList<>();
                String tempClaim = null;

                for (String roleName : userRolesClaims.keySet())
                {
                    System.out.println("Role: " + roleName);
                    tempClaim = roleName+":";
                    // using values() for iteration over values
                    for (Map<String, Map<String,Object>> permissionObjects : userRolesClaims.values()){
                        System.out.println("PermissionObjects: " + permissionObjects.keySet());
                        for (Map.Entry<String, Map<String, Object>> po : permissionObjects.entrySet()) //using map.entrySet() for iteration
                        {
                            System.out.println("PO name: " + po.getKey());
                            tempClaim=tempClaim+po.getKey()+":";
                            System.out.println("Value: " +  po.getValue());
                            if(po.getKey().equalsIgnoreCase("Role_Admin")){
                                //Give CRUD permission for /role api
                            }
                            for (Map.Entry<String, Object> entry : po.getValue().entrySet()){
                                System.out.println("Key1: " + entry.getKey());
                                if(entry.getKey().equalsIgnoreCase("action")){
                                    ArrayList<String> actt = (ArrayList<String>) entry.getValue();
                                    for (String actions:
                                            actt) {
                                        System.out.println(actions);
                                        tempClaim=tempClaim+actions+":";
                                    }
                                }else{
                                    Map<String,String> controlData = (Map<String, String>) entry.getValue();
                                    for (Map.Entry<String, String> cd : controlData.entrySet()){
                                        if(cd.getKey().equalsIgnoreCase("bu")){
                                            System.out.println("bu" + cd.getValue());
                                            tempClaim=tempClaim+cd.getValue()+":";
                                        }

                                        if(cd.getKey().equalsIgnoreCase("location")){
                                            System.out.println("location" +cd.getValue());
                                            tempClaim=tempClaim+cd.getValue()+":";
                                        }

                                    }
                                }
                            }
                            actualClaims.add(tempClaim);
                            tempClaim="";
                        }


                    }
                }

            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //Setting up the username for createdBy and UpdatedBy
                UserDTO userDto = new UserDTO();
                userDto.setEmail(email);
                UserContextHolder.setUserDto(userDto);

                UserDetails userDetails = service.loadUserByUsername(email);

                if (jwtUtil.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
