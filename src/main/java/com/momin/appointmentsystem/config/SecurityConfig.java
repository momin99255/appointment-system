package com.momin.appointmentsystem.config;
import com.momin.appointmentsystem.repository.UserRepository; import com.momin.appointmentsystem.security.JwtAuthenticationFilter; import lombok.RequiredArgsConstructor; import org.springframework.context.annotation.*; import org.springframework.http.HttpMethod; import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.core.userdetails.User; import org.springframework.security.core.userdetails.UserDetailsService; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @EnableMethodSecurity @RequiredArgsConstructor public class SecurityConfig {
 private final JwtAuthenticationFilter jwtAuthenticationFilter;
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UserRepository repo){return username->repo.findByEmail(username).map(u->User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole().name()).build()).orElseThrow(()->new IllegalArgumentException("User not found"));}
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{return http.csrf(csrf->csrf.disable()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(a->a
  .requestMatchers("/","/login","/register","/dashboard","/doctors","/css/**","/js/**","/api/auth/**","/actuator/health","/error").permitAll()
  .requestMatchers(HttpMethod.GET,"/api/doctors/**").permitAll()
  .requestMatchers("/api/admin/**").hasRole("ADMIN")
  .requestMatchers("/api/appointments/**").authenticated()
  .anyRequest().authenticated()).addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class).build();}
}
