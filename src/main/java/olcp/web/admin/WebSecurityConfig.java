//package olcp.web.admin;
//
//import olcp.service.CustomUserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Configuration
//@RequestMapping("/user")
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    UserDetailsService customUserService() {
//        return new CustomUserService();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").failureUrl("/error").permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//}