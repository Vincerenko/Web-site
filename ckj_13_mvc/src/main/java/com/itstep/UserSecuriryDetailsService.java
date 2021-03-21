package com.itstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserSecuriryDetailsService implements UserDetailsService {
	private UserRepository userRepository;

	@Autowired
	public UserSecuriryDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// найти пользователя по ИД
		// преобразовать его в ЮзерДетеилс и вернуть его из метода
		// если пользователь не найден, бросить ексепшен
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserSecurity(user);
	}

}
