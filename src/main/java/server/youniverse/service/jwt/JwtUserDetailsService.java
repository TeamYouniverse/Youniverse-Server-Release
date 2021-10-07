package server.youniverse.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        if("user_id".equals(name)){
            return new User("user_id", "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InVzZXJfaWQiLCJwYXNzd29yZCI6MTIzNH0.Xs6SvkiuYIVo_iVZkHpznqhzyqAd5wywcwDrtLh6Sgy6vY5J225eX4geiNE7DYZ1w3tRO5j9hSqTUUjN-mBTEQ",new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found with name : "+name);
        }
    }
}
