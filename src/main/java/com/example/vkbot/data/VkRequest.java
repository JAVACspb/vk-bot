package com.example.vkbot.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VkRequest {
    private String type;
    private VkObject object;
    private int group_id;
}
