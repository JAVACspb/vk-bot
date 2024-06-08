package com.example.vkbot.controller;

import com.example.vkbot.command.CommandRegistr;
import com.example.vkbot.command.VkCommand;
import com.example.vkbot.data.VkRequest;
import com.example.vkbot.service.VkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
public class VkController {

    private static final Logger logger = LoggerFactory.getLogger(VkController.class);
    private final CommandRegistr commandRegistry;

    @Autowired
    public VkController(CommandRegistr commandRegistry, VkService vkService) {
        this.commandRegistry = commandRegistry;
    }

    @PostMapping
    public String receiveEvent(@RequestBody VkRequest vkRequest) {
        logger.info("Received event: {}", vkRequest);
        String type = vkRequest.getType();

        if (type == null) {
            logger.warn("Request type is null");
            return "ok";
        }

        VkCommand command = commandRegistry.getCommand(type);

        if (command != null) {
            return command.execute(vkRequest);
        } else {
            logger.warn("Unknown event type: {}", type);
            return "ok";
        }
    }

}


