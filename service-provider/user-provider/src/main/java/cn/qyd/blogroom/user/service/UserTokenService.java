package cn.qyd.blogroom.user.service;

import cn.qyd.blogroom.user.entity.User;

public interface UserTokenService {
    String createOrRefreshToken(Long userId);

    Long validateToken(String token);
}
