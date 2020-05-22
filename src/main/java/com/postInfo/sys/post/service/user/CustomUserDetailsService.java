//package com.postInfo.sys.post.service.user;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import com.postInfo.sys.post.model.Role;
//import com.postInfo.sys.post.model.User;
//import com.postInfo.sys.post.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User dbUser = this.userRepository.findByUsername(username);
//
//        if (dbUser != null) {
//            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//            for (Role role : dbUser.getRoles()) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
//                grantedAuthorities.add(authority);
//            }
//
//            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
//                    dbUser.getUserName(), dbUser.getPassword(), grantedAuthorities);
//            return user;
//        } else {
//            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
//        }
//    }
//
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//
//}
//
