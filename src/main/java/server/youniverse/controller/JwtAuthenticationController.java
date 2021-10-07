package server.youniverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import server.youniverse.config.JwtTokenUtil;
import server.youniverse.domain.dto.JwtRequest;
import server.youniverse.domain.dto.JwtResponse;
import server.youniverse.service.jwt.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    //인증을 통한 토큰 발급

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value="/signIn",method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)throws Exception{
        authenticate(authenticationRequest.getName(),authenticationRequest.getPassword());

        final UserDetails userDetails=userDetailsService
                .loadUserByUsername(authenticationRequest.getName());

        final String token=jwtTokenUtil.generateToken(userDetails);
        System.out.println("toke"+token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String name,String password)throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name,password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED0,e");
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }

}
