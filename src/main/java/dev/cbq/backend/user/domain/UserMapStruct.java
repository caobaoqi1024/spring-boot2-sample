package dev.cbq.backend.user.domain;

import dev.cbq.backend.entity.User;
import dev.cbq.backend.user.domain.request.UserRegisterVo;
import dev.cbq.backend.user.domain.response.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapStruct {
    UserInfoVo toUserInfoVo(User user);
	User registerVo2User(UserRegisterVo vo);
}
