package com.ccs.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/login")
    public String login(String username, String password, Model model){

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        map.add("client_id", "javaboy");
        map.add("client_secret", "123");
        map.add("grant_type", "password");
//        map.add("redirect_uri", "http://localhost:8082/index.html");
        Map response = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
        System.out.println("response = " + response);
//        response = {access_token=074bd3ea-e0f9-449c-a3a3-e5a058c46db9, token_type=bearer, refresh_token=bc3d4214-8150-40e2-92ab-ded83b4cf88b, expires_in=7033, scope=all}response = {access_token=074bd3ea-e0f9-449c-a3a3-e5a058c46db9, token_type=bearer, refresh_token=bc3d4214-8150-40e2-92ab-ded83b4cf88b, expires_in=7033, scope=all}
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + response.get("access_token"));
        HttpEntity<?> httpParams = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, httpParams, String.class);
        model.addAttribute("msg", responseEntity.getBody());
        return "index.html";
    }

}
