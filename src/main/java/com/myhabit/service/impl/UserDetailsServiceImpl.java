package com.myhabit.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myhabit.entities.User;
import com.myhabit.entities.UserPrincipal;
import com.myhabit.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static String USER_NOT_EXIST = "User không tồn tại";
	

	@Autowired
	public UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = this.userRepository.findUserByName(username);
		
		if(!user.isPresent()) {
			throw new UsernameNotFoundException(USER_NOT_EXIST);
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.get().getRole().getName()));
		
		UserPrincipal userPrincipal = new UserPrincipal(user.get().getName(), user.get().getPassword(), authorities);
		userPrincipal.setEmail(user.get().getEmail());
		userPrincipal.setFirstName(user.get().getFirstName());
		userPrincipal.setLastName(user.get().getLastName());
		userPrincipal.setId(user.get().getId());
		return userPrincipal;
		
	}

}
