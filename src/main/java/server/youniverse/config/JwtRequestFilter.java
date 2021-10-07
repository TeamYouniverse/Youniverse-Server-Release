package server.youniverse.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import server.youniverse.service.jwt.JwtUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    /*
    *request 요청시, 먼저 거침(interception 개념)
     */

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException{
        final String requestTokenHeader=request.getHeader(("Authorization"));

        String name=null;
        String jwtToken=null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")){
        jwtToken=requestTokenHeader.substring(7);
        try{
            name=jwtTokenUtil.getUsernameFromToken(jwtToken);
        }catch(IllegalArgumentException e){
            System.out.println("Unable to get JWT Token");

        }catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }
        }else{
            logger.warn("JWT Token does not begin with Bearer String");
        }

        //success validate
        if(name!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.jwtUserDetailsService.loadUserByUsername(name);

            //authentication
            if(jwtTokenUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
