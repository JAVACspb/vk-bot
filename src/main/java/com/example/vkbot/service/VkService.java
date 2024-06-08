package com.example.vkbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class VkService {

    @Value("${vk.access-token}")
    private String accessToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(int peerId, String message) {
        String url = "https://api.vk.com/method/messages.send";

        Map<String, String> params = new HashMap<>();
        params.put("peer_id", String.valueOf(peerId));
        params.put("message", message);
        params.put("random_id", String.valueOf(new Random().nextInt(1000000)));
        params.put("access_token", accessToken);
        params.put("v", "5.131");

        // Логирование URL и параметров
        System.out.println("URL: " + url);
        params.forEach((key, value) -> System.out.println(key + ": " + value));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Формирование тела запроса
        StringBuilder requestBody = new StringBuilder();
        params.forEach((key, value) -> requestBody.append(key).append("=").append(value).append("&"));
        if (requestBody.length() > 0) {
            requestBody.setLength(requestBody.length() - 1);  // Remove the trailing '&'
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // Логирование тела запроса
        System.out.println("Тело запроса: " + requestBody);

        // Отправка запроса и логирование ответа
        String response = restTemplate.postForObject(url, entity, String.class);
        System.out.println("Ответ от VK: " + response);
    }
}
