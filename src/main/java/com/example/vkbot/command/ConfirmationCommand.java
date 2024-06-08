package com.example.vkbot.command;

import com.example.vkbot.data.VkRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationCommand implements VkCommand{
    @Value("${vk.confirmation-code}")
    private String confirmationCode;

    @Override
    public String execute(VkRequest vkRequest) {
        return confirmationCode;
    }

}
