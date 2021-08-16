package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UsersDAO usersDAO;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        for (User user: usersDAO.getAllUsers().getUsersList()) {
            auth.inMemoryAuthentication()
                    .withUser(user.getLogin())
                    .password("{noop}" + user.getPassword())
                    .roles("USER");
        }
    }

}