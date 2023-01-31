package com.msciq.storage.config;

import com.msciq.storage.common.Constants;
import com.msciq.storage.security.JwtFilter;
import com.msciq.storage.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedMethods(Arrays.asList(HttpMethod.DELETE.name(), HttpMethod.GET.name(),
                            HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.PATCH.name()));
                    cors.applyPermitDefaultValues();
                    cors.addAllowedOrigin(Constants.ASTERISK_SYMBOL);
                    cors.addAllowedOriginPattern(Constants.ASTERISK_SYMBOL);
                    return cors;
                }).and().authorizeRequests().antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/swagger-ui/index.html").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/javainuse-openapi/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll().anyRequest().authenticated().and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().httpBasic();
        return http.build();
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagger-ui.html", "/swagger-ui")
//                .permitAll().anyRequest().authenticated()
//                .and().exceptionHandling().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }
}
