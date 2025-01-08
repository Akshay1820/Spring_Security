package project.security.spring_security.Controller.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                requests ->
                        requests.requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
        ;

        return http.build();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        UserDetails user1 = User.withUsername("akshay")
                .password(encodePassword().encode("pass1"))
                .roles("USER")
                .build();

//        UserDetails adminUser=User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
        return saveUser(user1);

    }

    private UserDetailsManager saveUser(UserDetails userDetails) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        if (!userDetailsManager.userExists(userDetails.getUsername())) {
            userDetailsManager.createUser(userDetails);
        }
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

}
