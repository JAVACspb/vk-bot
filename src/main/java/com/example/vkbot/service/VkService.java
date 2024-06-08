package com.example.vkbot.service;

import com.example.vkbot.data.VkMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
@Slf4j
public class VkService {

    @Value("${vk.access-token}")
    private String accessToken;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://api.vk.com/method/messages.send";

    public void sendMessage(VkMessage message) {

        Map<String, String> params = new HashMap<>();
        params.put("peer_id", String.valueOf(message.getPeerId()));
        params.put("message", message.getText());
        params.put("random_id", String.valueOf(new SecureRandom().nextInt(1000000)));
        params.put("access_token", accessToken);
        params.put("v", "5.131");

        log.debug("params : {}", params);

        // Формирование тела запроса
        StringBuilder requestBody = new StringBuilder();
        params.forEach((key, value) -> requestBody.append(key).append("=").append(value).append("&"));
        if (!requestBody.isEmpty()) {
            requestBody.setLength(requestBody.length() - 1);  // Remove the trailing '&'
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // Логирование тела запроса
        log.debug("Тело запроса: {}", requestBody);

        // Отправка запроса и логирование ответа
        String response = restTemplate.postForObject(URL, entity, String.class);
        log.info("Ответ от VK: {}", response);
    }
}
