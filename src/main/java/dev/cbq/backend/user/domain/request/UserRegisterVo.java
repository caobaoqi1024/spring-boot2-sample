package dev.cbq.backend.user.domain.request;

import lombok.Data;

@Data
public class UserRegisterVo {

	private String username;
	private String email;
	private String password;

}
