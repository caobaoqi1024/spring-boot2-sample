package dev.cbq.backend.user.domain.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoVo {
    private String username;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
