package fsoft.mock_project.movie_theater.security.service;

import fsoft.mock_project.movie_theater.security.model.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private AccountService accountService;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername method of UserDetailService");
        Account account = accountService.findByUsername(username);
        User user = new User(account.getUsername(), passwordEncoder.encode(account.getPassword()),
                Collections.singleton(new SimpleGrantedAuthority(account.getRole().getRoleName().roleName))
        );

        log.info("Finish loadUserByUsername method of UserDetailService");
        return user;
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(
//            Collection<Role> roles) {
//        List<GrantedAuthority> authorities
//                = new ArrayList<>();
//        for (Role role: roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        return authorities;
//    }
}
