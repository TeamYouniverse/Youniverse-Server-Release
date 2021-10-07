package server.youniverse.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import server.youniverse.interceptor.JwtInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"server.youniverse.controller","server.youniverse.interceptor"})
public class ServletContext implements WebMvcConfigurer {

    //interceptor 등록
    @Autowired
    JwtInterceptor jwtInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        //client에서 header 추출 가능
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("jwt-auth-token");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //인터셉터 등록
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") //interceptor 적용될 경로
                .excludePathPatterns(new String[] {"excludePath/**"}); //적용되지 않을 경로
    }
}
