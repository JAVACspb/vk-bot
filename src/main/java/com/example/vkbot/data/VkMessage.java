package com.example.vkbot.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VkMessage {
    @JsonProperty("peer_id")
    private int peerId;
    private String text;

}

