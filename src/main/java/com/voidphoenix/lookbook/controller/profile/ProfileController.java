package com.voidphoenix.lookbook.controller.profile;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String displayProfile(final ProfileForm profileForm){
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile", params = {"save"},  method = RequestMethod.POST)
    public String saveProfile(@Valid final ProfileForm profileForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/profilePage";
        }
        System.out.println("Saving " + profileForm);
        return "redirect:profile";
    }

    @RequestMapping(value = "profile", params = {"addWish"})
    public String addWish(ProfileForm profileForm) {
        profileForm.getWishes().add(null);
        return "profile/profilePage";
    }

    @RequestMapping(value = "profile", params = {"removeWish"})
    public String removeWish(ProfileForm profileForm, HttpServletRequest request) {
        Integer rowId = Integer.valueOf(request.getParameter("removeWish"));
        profileForm.getWishes().remove(rowId.intValue());
        return "profile/profilePage";
    }



}
