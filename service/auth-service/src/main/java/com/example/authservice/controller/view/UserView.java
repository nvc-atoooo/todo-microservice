package com.example.authservice.controller.view;

import com.example.authservice.domain.User;
import lombok.Value;

@Value
public class UserView {
    private String userName;
    private boolean enabled;
    private long id;

    public UserView(long id, String userName, boolean enabled) {
        this.id = id;
        this.userName = userName;
        this.enabled = enabled;
    }

    public UserView(User user) {
        this(user.getUser_id(), user.getUsername(), user.isEnabled());
    }
}
