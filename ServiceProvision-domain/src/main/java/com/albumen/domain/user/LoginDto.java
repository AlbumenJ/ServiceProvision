package com.albumen.domain.user;

import lombok.Data;

@Data
public class LoginDto {
    private Integer userId;
    private String username;
    private Integer roleId;
    private String roleName;
    private String token;
}
