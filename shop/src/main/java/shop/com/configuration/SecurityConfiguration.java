package shop.com.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import shop.com.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	GoogleOAuth2SuccessHandler GoogleOAuth2SuccessHandler;
	@Autowired
	CustomUserDetailService customUserDetailService;
	

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http   
		      .authorizeRequests()
		      .antMatchers ( "/", "/shop/**", "/register", "/mysql-console/**").permitAll()
		      .antMatchers ("/admin/**").hasRole ("ADMIN")
		      .anyRequest()
		      .authenticated()
		      .and()
		      .formLogin()
		      .loginPage("/login/")
		      .permitAll()
		      .failureUrl("/login?error= true")
		      .defaultSuccessUrl("/")
		      .usernameParameter ("email")
		      .passwordParameter ("password")
		      .and()
		      .oauth2Login()
		      .loginPage("/login")
		      .successHandler(GoogleOAuth2SuccessHandler)
		      .and()
		      .logout()
		      .logoutRequestMatcher (new AntPathRequestMatcher ("/logout"))
		      .logoutSuccessUrl ("/login")
		      .invalidateHttpSession(true)
		      .deleteCookies ("JSESSIONID")
		      .and()
		      .exceptionHandling()
		      .and() 
		      .csrf()
		      .disable();
		
		http.headers().frameOptions().disable();
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();		
	}
	
	
	public void configure (AuthenticationManagerBuilder auth ) throws Exception { 
		auth.userDetailsService(customUserDetailService);
	}
	
	public void configure (WebSecurity web) throws Exception {
		web.ignoring().antMatchers ("/resources/**", "/static/**", "/images/**", "/productimages/**", "/css/**", "/js/**" );
	}
}
