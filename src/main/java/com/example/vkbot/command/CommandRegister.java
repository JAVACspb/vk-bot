package com.example.vkbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandRegister {
    private final Map<String, VkCommand> commandMap = new HashMap<>();

    @Autowired
    public CommandRegister(ConfirmationCommand confirmationCommand, MessageNewCommand messageNewCommand) {
        commandMap.put("confirmation", confirmationCommand);
        commandMap.put("message_new", messageNewCommand);
    }

    public VkCommand getCommand(String type) {
        if (!commandMap.containsKey(type)) {
            throw new IllegalStateException(String.format("Event type unknown : type - %s", type));
        }
        return commandMap.get(type);
    }

}
