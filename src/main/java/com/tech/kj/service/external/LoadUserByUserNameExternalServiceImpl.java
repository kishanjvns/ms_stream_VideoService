package com.tech.kj.service.external;

import com.tech.kj.service.external.dto.RegisterUserDtoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadUserByUserNameExternalServiceImpl implements LoadUserByUserNameExternalService{
    private final RestTemplate restTemplate;
    @Override
    public RegisterUserDtoResponse fetchUserByUserName(String userName,String token) {
        String url =basePath+String.format(resource_GetByUserName,userName);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<RegisterUserDtoResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, RegisterUserDtoResponse.class);
        log.info("getting response from uri: {} \n {}",url,response.getBody());
        return response.getBody();
    }
}
