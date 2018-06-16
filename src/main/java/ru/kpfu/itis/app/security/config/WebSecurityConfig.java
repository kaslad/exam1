package ru.kpfu.itis.app.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@ComponentScan("ru.kpfu.itis.app")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()


                .antMatchers("/registration").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/js/*").permitAll()

                .antMatchers("/").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(86400);

        http.csrf().disable();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider);
        */
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        /*JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
        */
        return  null;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}