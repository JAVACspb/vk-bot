package com.example.vkbot.controller;

import com.example.vkbot.command.CommandRegister;
import com.example.vkbot.command.VkCommand;
import com.example.vkbot.data.VkRequest;
import com.example.vkbot.service.VkService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
@Slf4j
public class VkController {
    private final CommandRegister commandRegistry;

    @Autowired
    public VkController(CommandRegister commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @PostMapping
    public String receiveEvent(@RequestBody VkRequest vkRequest) {
        log.info("Received event: {}", vkRequest);
        String type = vkRequest.getType();

        VkCommand command = commandRegistry.getCommand(type);

        return command.execute(vkRequest);
    }
}


