package shop.com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shop.com.model.CustomUserDetail;
import shop.com.model.User;
import shop.com.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userrepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	  Optional <User> user = userrepo.findByEmail(email);
	  user.orElseThrow(()-> new UsernameNotFoundException("User to nahi mila"));
	  return user.map(CustomUserDetail :: new).get();
	
	}

}
