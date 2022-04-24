package ox.mox.Rest.Configurations;


import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;



@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    private final LoginSuccessUserHandler successUserHandler;

    public WebSecurityConfiguration(@Qualifier("userDetailsServiceImpl")
                                    UserDetailsService userDetailsService, LoginSuccessUserHandler successUserHandler) {

        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;

    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }


    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .loginPage("/login")
                .successHandler(successUserHandler)
                .usernameParameter("email")
                .permitAll();


        http
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")

                .and()
                    .csrf().disable();


        http
                .authorizeRequests()

                .mvcMatchers("/login")
                    .permitAll()

                .mvcMatchers("/user/**")
                    .hasAnyRole("USER","ADMIN")

                .mvcMatchers("/admin/**")
                    .hasRole("ADMIN")

                .anyRequest()
                .authenticated();
    }
}
