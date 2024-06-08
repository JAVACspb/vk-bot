package com.example.vkbot.command;

import com.example.vkbot.data.VkMessage;
import com.example.vkbot.data.VkRequest;
import com.example.vkbot.service.VkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageNewCommand implements VkCommand {

    private final VkService vkService;

    public MessageNewCommand(VkService vkService) {
        this.vkService = vkService;
    }

    @Override
    public String execute(VkRequest vkRequest) {
        VkMessage message = vkRequest.getObject().getMessage();
        vkService.sendMessage(message); // Отправка полученного сообщения обратно
        log.info("Handled new message event: sent message to peerId {}", message.getPeerId());
        return "ok";
    }

}
