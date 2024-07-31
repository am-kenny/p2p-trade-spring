package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.persistence.entities.UserVerification;
import com.mycompany.p2pTradeSpringProject.service.UserVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(Urls.VERIFY)
@AllArgsConstructor
public class VerificationController {

    private final UserVerificationService userVerificationService;

    @GetMapping
    public String verify(HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") == null) {
            return "redirect:" + Urls.LOGIN; // Redirect to login page if user is not authenticated
        }
        return "verify";
    }

    @PostMapping
    public String verifyPost(@RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("passport_number") String passport_number,
                             @RequestParam("passport_photo") MultipartFile passport_photo,
                             HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") == null) {
            return "redirect:" + Urls.LOGIN; // Redirect to login page if user is not authenticated
        }

        try {
            //save photo file locally
            String filePath = "D:\\IdeaProjects\\p2pTradeSpringProject\\src\\main\\resources\\static\\passport_images\\"; //TODO: Change this to a more appropriate location
            passport_photo.transferTo(new File(filePath + passport_photo.getOriginalFilename()));

            UserVerification userVerification = new UserVerification();
            userVerification.setName(name);
            userVerification.setSurname(surname);
            userVerification.setPassportNumber(passport_number);
            userVerification.setPassportPhotoReference("passport_images/" + passport_photo.getOriginalFilename());

            User user = (User) request.getSession().getAttribute("authenticatedUser");
            userVerificationService.verifyUser(user.getId(), userVerification);
        } catch (IOException e) {
            return "redirect:" + Urls.VERIFY; // Redirect back to verify page on failure
        }

        return "redirect:" + Urls.PROFILE; // Redirect to profile page on success
    }

}
