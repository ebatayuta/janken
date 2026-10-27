package oit.is.z3406.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken/**").authenticated()
            .anyRequest().permitAll());
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$05$zRj6CHA4v7kqdaa7uo79wenx2pxyk7ITqQHUdJEFDMcehPufprfhe").roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$05$zRj6CHA4v7kqdaa7uo79wenx2pxyk7ITqQHUdJEFDMcehPufprfhe").roles("USER").build();
    UserDetails hnd = User.withUsername("ほんだ")
        .password("{bcrypt}$2y$05$e6ee8thQcGXtW6O0APEfUuykJ5tM76KJZmJID8ac1tm6yMDb7.qae").roles("USER").build();

    return new InMemoryUserDetailsManager(user1, user2, hnd);
  }

}
