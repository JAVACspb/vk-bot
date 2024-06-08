package com.example.vkbot.command;

import com.example.vkbot.data.VkRequest;

public interface VkCommand {
    String execute(VkRequest vkRequest);

}
