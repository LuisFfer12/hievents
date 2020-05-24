package br.com.hievents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.hievents.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioServiceImpl)
            .passwordEncoder(passwordEncoder());
    }
	
	 @Override
	    protected void configure( HttpSecurity http ) throws Exception {
	        http
	            .csrf().disable()
	            .authorizeRequests()
	                .antMatchers("/anunciante/**")
	                    .hasRole("USER")
	                .antMatchers(HttpMethod.POST, "/eventos")
	                    .hasRole("USER")
	                .antMatchers(HttpMethod.POST,"/usuarios/**")
	                    .permitAll()
	                .antMatchers(HttpMethod.GET, "/eventos/**")
	                    .permitAll()
	            .and()
	                .formLogin().loginPage("/login");
	        ;
	    }
}
