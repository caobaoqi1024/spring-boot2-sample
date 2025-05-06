package dev.cbq.backend.security.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

	private final List<String> users = new ArrayList<>();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		users.add("user");
		users.add("admin");
		if (!users.contains(username)) {
			throw new UsernameNotFoundException(String.format("User %s not found", username));
		}
		if (username.equals("admin")) {
			return User
				.withUsername("admin")
				.password("{bcrypt}$2a$10$K0Xr3gPer2nLbyWfEWKbBe/j9a./DjuQhx/aJv1IlBuTXGObFNc6G")
				.roles("admin")
				.build();
		}
		if (username.equals("user")) {
			return User
				.withUsername("user")
				.password("{bcrypt}$2a$10$pre528Ywa4n9HfyhpydTUeH3Xlj3FveSH2vBN7Bb/N.Mo.RasnwYm")
				.roles("user")
				.build();
		}
		return null;
	}
}
