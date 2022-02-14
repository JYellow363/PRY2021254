package pe.edu.upc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.edu.upc.config.JWTAuthorizationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class PRY2021254Application {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(PRY2021254Application.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().ignoringAntMatchers(
			        "/v2/api-docs", 
			        "/swagger-resources/configuration/ui",     
			        "/swagger-resources", 
			        "/swagger-resources/configuration/security", 
			        "/swagger-ui.html",     
			        "/webjars/**");
			
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests().antMatchers(HttpMethod.POST, "/guardians/create").permitAll()
					.antMatchers(HttpMethod.POST, "/guardians/login").permitAll()
					.antMatchers(HttpMethod.GET, "/guardians/restorePassword").permitAll()
					.antMatchers(HttpMethod.POST, "/specialists/login").permitAll()
					.antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/swagger-resources/**").permitAll()
					.antMatchers("/**").authenticated();

		}
	}
}
