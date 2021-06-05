package com.albumen.domain.user;

public interface UserService {

    LoginDto login(String username, String password);
}
