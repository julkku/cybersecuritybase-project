package sec.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        Account account = new Account();
        account.setUsername("jules");
        account.setPassword("jules");
        account.setFunds(5);
        accountRepository.save(account);

        account = new Account();
        account.setUsername("soini");
        account.setPassword("soini");
        account.setFunds(10);
        accountRepository.save(account);

        account = new Account();
        account.setUsername("punkku-seppo");
        account.setPassword("ps");
        account.setFunds(10);
        accountRepository.save(account);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}

