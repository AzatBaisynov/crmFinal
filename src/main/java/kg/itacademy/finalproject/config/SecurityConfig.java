package kg.itacademy.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login, password, status from users where login=?")
                .authoritiesByUsernameQuery("select u.login, ur.role_name from user_role ur inner join users u on ur.user_id = u.id where u.login=? and u.status = 1");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
//                .anyRequest().permitAll()
                .antMatchers(HttpMethod.POST, "/login/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/login/registration/user").hasRole("ADMIN")
//
//                .antMatchers(HttpMethod.POST, "/purchase").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/purchase/create").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/purchase/list").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/purchase/list").hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/purchase/create").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/purchase").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/purchase/list").hasAnyRole()
//                .antMatchers(HttpMethod.GET, "/purchase/list").hasAnyRole()
//
//                .antMatchers(HttpMethod.POST, "/storage").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/storage/create").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/storage/list").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/storage").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/storage/create").hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/storage/list").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/storage/list").hasAnyRole()
//                .antMatchers(HttpMethod.GET, "/storage/list").hasAnyRole()
//                .antMatchers(HttpMethod.POST, "/storage").hasAnyRole()
//                .antMatchers(HttpMethod.GET, "/storage").hasAnyRole()
//
//
//                .antMatchers(HttpMethod.POST, "/sales").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/sales/list").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/sales/create").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/storage").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/storage/create").hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/storage/list").hasRole("USER")
//
//                .antMatchers(HttpMethod.GET, "/image").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/image").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/image").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/image").hasRole("USER")

//                .antMatchers(HttpMethod.PUT, "/user").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/test").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT," /user/admin").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/services").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/services").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/services").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/services").hasRole("ADMIN")
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
