package com.financeapp.api.domain.auth;

import com.financeapp.api.domain.user.RegisterRequestDTO;
import com.financeapp.api.domain.user.UserAccount;
import com.financeapp.api.domain.user.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username);
    }

    public void register(RegisterRequestDTO request) {
        var encoder = new BCryptPasswordEncoder(16);

        var user = new UserAccount();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setUsername(request.username());
        userAccountRepository.save(user);

        setupAccount();
    }

    private void setupAccount() {
        // TODO create categories and default account
    }

}
