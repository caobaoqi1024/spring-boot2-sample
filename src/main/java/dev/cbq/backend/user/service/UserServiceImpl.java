package dev.cbq.backend.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.cbq.backend.entity.User;
import dev.cbq.backend.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
