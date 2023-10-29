package com.mobileskins.userservice.security;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity(debug = true)
public class SpringSecurityConfig {
	
	@SuppressWarnings("unused")
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//Authorizing every http request using basic authentication
		http.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize)->{
				
//				authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//				authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//				authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//				authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
				
				authorize.requestMatchers(HttpMethod.GET, "/api/users/**").permitAll();
				authorize.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll();
				authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

				authorize.anyRequest().authenticated();	
		}).httpBasic(Customizer.withDefaults());
		
		return http.build();
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
    
    //As we are using database authentication, commenting out in-memory authentication code written for testing during development.
	
//	@Bean
//	UserDetailsService userDetailsService() {
//	
//	//Create 2 in-memory user
//	UserDetails jagadish = User.builder()
//							   .username("ramesh")
//							   .password(passwordEncoder().encode("ramesh12345"))
//							   .roles("USER")
//							   .build();
//	
//	UserDetails admin = User.builder()
//							.username("admin")
//							.password(passwordEncoder().encode("admin12345"))
//							.roles("ADMIN")
//							.build();
//	
//	return new InMemoryUserDetailsManager(jagadish, admin);
//	
//	}
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    	
}

