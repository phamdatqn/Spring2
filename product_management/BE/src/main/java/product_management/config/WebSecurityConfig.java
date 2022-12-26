package product_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import product_management.jwt.JwtFilter;
import product_management.service.security.impl.MyUserDetailService;

@Configuration
@EnableWebSecurity
@CrossOrigin("*")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService userDetailService;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*cài đặt lấy thông tin của userDetail và mã hóa passs*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable().
                authorizeRequests()
                .antMatchers("/api/security/**",
                        "/api/public/list**",
                        "/api/public/detail**"
                )
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers("/api/public/cart-list/{username}",
                        "/api/public/add-cart**",
                        "/api/public/desc-quantity**",
                        "/api/public/asc-quantity**",
                        "/api/public/total-bill**",
                        "/api/public/sum-quantity-cart**",
                        "/api/public/delete-cart**",
                        "/api/public/payment**",
                        "/api/public/history**",
                        "/api/info/{username}",
                        "/api/public/product-size**").hasRole("CUSTOMER")

                .and()
                .authorizeRequests()
                .antMatchers("/api/public/delete/{id}",
                        "/api/public/update**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()

                .and().cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(60 * 60 * 24);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
