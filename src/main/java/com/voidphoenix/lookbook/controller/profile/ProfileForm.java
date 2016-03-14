package com.voidphoenix.lookbook.controller.profile;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;


public class ProfileForm {

    @Email
    @NotEmpty
    private String email;

    private List<String> wishes = new ArrayList<>();

    public List<String> getWishes() {
        return wishes;
    }

    public void setWishes(List<String> wishes) {
        this.wishes = wishes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
