package dev.cbq.backend.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import dev.cbq.backend.common.Result;
import dev.cbq.backend.entity.User;
import dev.cbq.backend.user.domain.UserMapStruct;
import dev.cbq.backend.user.domain.request.UserRegisterVo;
import dev.cbq.backend.user.domain.response.UserInfoVo;
import dev.cbq.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapStruct userMapStruct;

	@GetMapping
	public Result<List<UserInfoVo>> list() {
		List<UserInfoVo> list = userService.list().stream()
			.map(userMapStruct::toUserInfoVo)
			.collect(Collectors.toList());
		return Result.success(list);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Result<UserInfoVo> add(@RequestBody UserRegisterVo vo) {
		User user = userMapStruct.registerVo2User(vo);
		boolean saved = userService.save(user);
		if (saved) {
			return Result.success(userMapStruct.toUserInfoVo(
				userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, user.getEmail()))
			));
		}
		return Result.error();
	}

}
