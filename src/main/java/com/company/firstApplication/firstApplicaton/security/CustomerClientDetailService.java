package com.company.firstApplication.firstApplicaton.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerClientDetailService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public CustomerClientDetailService(ClientRepository clientRepository) {
        super();
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String clientName) throws UsernameNotFoundException {
        Client client = this.clientRepository.findByClientName(clientName);
        if(null == client) {
            throw new UsernameNotFoundException("cannot find username: "+clientName);
        }
        log.info("Client details={}",client);
        return new CustomUserPrincipal(client);

    }
}
