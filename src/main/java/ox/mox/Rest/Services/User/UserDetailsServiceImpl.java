package ox.mox.Rest.Services.User;

import ox.mox.Rest.Models.User;
import ox.mox.Rest.Repositories.User.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.readByEmail(email);

        if (user == null) {

            throw new UsernameNotFoundException("Лузер не фаунд! Рыскай дальше");
        }

        return user;
    }


}
