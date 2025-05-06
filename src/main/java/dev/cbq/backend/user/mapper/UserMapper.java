package dev.cbq.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.cbq.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
