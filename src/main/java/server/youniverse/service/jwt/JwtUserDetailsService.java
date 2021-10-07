package server.youniverse.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.youniverse.domain.dto.UserDto;
import server.youniverse.repository.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {


    /*
    @param name
    @return User
     */
    @Override
    public User loadUserByName(String name) throws UsernameNotFoundException{
        return userRepository.findByName(name)
                .orElseThrow(()-> new UsernameNotFoundException(name));
    }


    /*
    유저 정보 저장
    @param UserDTO
    @return user_id
     */
    public Long save(UserDto userDto){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        return userRepository.save(User.builder())
                .name(userDto.getName())
                .password(userDto.getPassword()).build().getId();
    }



}
