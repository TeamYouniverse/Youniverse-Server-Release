package server.youniverse.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import server.youniverse.service.jwt.JwtServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtServiceImpl jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        if(request.getMethod().equals("OPTIONS")){
            //preflight로 넘어온 options 통과
            return true;
        }else{
            String token=request.getHeader("jwt-auth-token");//client header
            if(token!=null && token.length()>0){
                jwtService.checkValid(token);//토큰 유효성 검증
                return true;
            }else{
                throw new Exception("유효한 인증 토큰이 존재하지 않습니다.");
            }
        }
    }
}
