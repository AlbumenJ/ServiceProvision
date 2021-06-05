package com.albumen.domain.user;

import com.albumen.auth.util.SecurityUtil;
import com.albumen.user.Role;
import com.albumen.user.RoleMapper;
import com.albumen.user.User;
import com.albumen.user.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public LoginDto login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password);

        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            Role role = roleMapper.selectById(user.getRoleId());
            String token = securityUtil.login(user.getUserId().toString(), Collections.singletonList(role.getName()), user);

            LoginDto loginDto = new LoginDto();
            loginDto.setUserId(user.getUserId());
            loginDto.setUsername(user.getUsername());
            loginDto.setRoleId(user.getRoleId());
            loginDto.setRoleName(role.getName());
            loginDto.setToken(token);

            return loginDto;
        }
        return null;
    }
}
