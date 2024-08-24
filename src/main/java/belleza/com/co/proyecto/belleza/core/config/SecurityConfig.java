package belleza.com.co.proyecto.belleza.core.config;

import belleza.com.co.proyecto.belleza.core.util.JwtFilterUtil;
import belleza.com.co.proyecto.belleza.service.CredencialService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import jakarta.servlet.Filter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final CredencialService userService;
    private  final  BCryptPasswordEncoder passwordEncoder;
    private final JwtFilterUtil jwtFilterUtil;

    @Value("${api.base.path}")
    private String basePath;

    public SecurityConfig(CredencialService userService, BCryptPasswordEncoder passwordEncoder, JwtFilterUtil jwtFilterUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilterUtil = jwtFilterUtil;
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(authRequest -> authRequest
                                .requestMatchers(HttpMethod.POST, basePath + "/usuario/registro").permitAll()
                                .requestMatchers(HttpMethod.POST, basePath + "/credencial/login").permitAll()
                                .requestMatchers(HttpMethod.POST, basePath + "/certificado/login").hasRole("profesional")

                                .anyRequest().authenticated())
                .addFilterBefore((Filter) jwtFilterUtil, UsernamePasswordAuthenticationFilter.class)


                .build();
    }




    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider)
                .build();
    }
}

