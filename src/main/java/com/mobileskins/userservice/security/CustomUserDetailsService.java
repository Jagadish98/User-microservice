package com.mobileskins.userservice.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobileskins.userservice.entity.Customer;
import com.mobileskins.userservice.reprository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		System.out.println("Entered loadUserByUsername");
		
		//Fetch user object from database
		Customer user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
								  .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist with this username or email."));
		
		System.out.println("user-before : " + user.toString());
		//user has set of roles, convert this Set<Roles> to Set<GrantedAuthorities>
		Set<GrantedAuthority> authorities = user.getRoles()
												.stream()
												.map((role) -> { 
													System.out.println("processing role : " + role.toString());
													return new SimpleGrantedAuthority(role.getName());
															}
														)
												.collect(Collectors.toSet());
		
		System.out.println("user : " + user.toString());
		System.out.println("Exited loadUserByUsername");
		
		//return the User object created by spring security
		return new User(
				usernameOrEmail,
				user.getPassword(),
				authorities
				);
	}

}
