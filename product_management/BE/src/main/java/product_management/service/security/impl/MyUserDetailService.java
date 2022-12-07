package product_management.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import product_management.model.User;
import product_management.repository.IUserRepository;

@Service
@Transactional
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Tài khoản: " + username + "Không tồn tại !");
        }
        return new MyUserDetails(user);
    }
}
