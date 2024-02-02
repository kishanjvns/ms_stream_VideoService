package com.tech.kj.service.external;

import com.tech.kj.service.external.dto.RegisterUserDtoResponse;

public interface LoadUserByUserNameExternalService {
    static final String basePath="http://localhost:8081";
    static final String resource_GetByUserName="/users/api/v1/user/%s";
    public RegisterUserDtoResponse fetchUserByUserName(String userName,String token);
}
