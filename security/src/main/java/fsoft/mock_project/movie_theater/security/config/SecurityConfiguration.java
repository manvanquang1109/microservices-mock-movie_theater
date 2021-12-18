package fsoft.mock_project.movie_theater.security.config;

import fsoft.mock_project.movie_theater.security.model.common.RoleName;
import fsoft.mock_project.movie_theater.security.service.UserService;
import fsoft.mock_project.movie_theater.security.filter.JwtFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private JwtFilter jwtFilter;

    private static final String[] movieAntPatterns = {"/api/v1/movie/**", "/api/v1/show-date/**", "/api/v1/schedule/**", "/api/v1/type/**"};
    private static final String[] adminAndEmployeeAuthorities = {RoleName.EMPLOYEE.roleName, RoleName.ADMIN.roleName};

    @Bean
    public static PasswordEncoder passwordEncoder() {
        log.info("Inside PasswordEncoder bean of SecurityConfiguration");
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("Inside configure AuthenticationManagerBuilder method of SecurityConfiguration");
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Inside configure HttpSecurity method of SecurityConfiguration");
        http.csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/api/v1/role/**").hasAuthority(RoleName.ADMIN.roleName)
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/account/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/v1/invoice/**").hasAuthority(RoleName.MEMBER.roleName)
//
//                .antMatchers(HttpMethod.GET, movieAntPatterns).permitAll()
//                .antMatchers(movieAntPatterns).hasAnyAuthority(adminAndEmployeeAuthorities)
//
//                .antMatchers("/api/v1/cinema-room/**", "api/v1/seat/**").hasAnyAuthority(adminAndEmployeeAuthorities)
//
//                .antMatchers(HttpMethod.GET, "/api/v1/schedule-seat/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        log.info("Inside AuthenticationManager bean of SecurityConfiguration");
        return super.authenticationManager();
    }
}
