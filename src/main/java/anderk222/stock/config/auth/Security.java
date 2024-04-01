package anderk222.stock.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/shopping/**").permitAll()
                        .requestMatchers("/register.html").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((fconf) -> fconf
                        .loginPage("/login.html").permitAll()
                        .failureUrl("/login-error.html")
                        .permitAll()
                        .defaultSuccessUrl("/"))
                .logout(Customizer.withDefaults())

                .httpBasic(Customizer.withDefaults())
                .csrf((c) -> c.disable());
        // .cors(c->c.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return new CustomUserDetailService();
    }

    @Bean
    public AuthenticationProvider provider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(nEncoder());

        provider.setUserDetailsService(userDetailsService());

        return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();

    }

    @Bean
    BCryptPasswordEncoder nEncoder() {
        return new BCryptPasswordEncoder();
    }

}
