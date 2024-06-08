package com.example.vkbot.command;

import com.example.vkbot.data.VkMessage;
import com.example.vkbot.data.VkRequest;
import com.example.vkbot.service.VkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageNewCommand implements VkCommand {
    private static final Logger logger = LoggerFactory.getLogger(MessageNewCommand.class);

    private final VkService vkService;

    public MessageNewCommand(VkService vkService) {
        this.vkService = vkService;
    }

    @Override
    public String execute(VkRequest vkRequest) {
        VkMessage message = vkRequest.getObject().getMessage();
        if (message != null) {
            int peerId = message.getPeerId();
            String text = message.getText();
            vkService.sendMessage(peerId, text); // Отправка полученного сообщения обратно
            logger.info("Handled new message event: sent message to peerId {}", peerId);
        } else {
            logger.warn("Message object is null");
        }
        return "ok";
    }

}
