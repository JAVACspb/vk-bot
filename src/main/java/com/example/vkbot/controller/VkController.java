package com.example.vkbot.controller;

import com.example.vkbot.data.VkMessage;
import com.example.vkbot.data.VkRequest;
import com.example.vkbot.service.VkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
public class VkController {

    private final VkService vkService;

    @Value("${vk.confirmation-code}")
    private String confirmationCode;

    public VkController(VkService vkService) {
        this.vkService = vkService;
    }

    @PostMapping
    public String receiveEvent(@RequestBody VkRequest vkRequest) {
        switch (vkRequest.getType()) {
            case "confirmation" -> {
                return confirmationCode;
            }
            case "message_new" -> {
                VkMessage message = vkRequest.getObject().getMessage();
                int peerId = message.getPeerId();
                String text = message.getText();
                vkService.sendMessage(peerId, text); // Отправка полученного сообщения обратно
                return "ok";
            }
        }
        return "ok";
    }
}

