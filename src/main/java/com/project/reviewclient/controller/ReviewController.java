package com.project.reviewclient.controller;

import com.project.reviewclient.model.Review;
import com.project.reviewclient.model.User;
import com.project.reviewclient.service.ReviewApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewApiClient apiClient;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("reviews", apiClient.getAllReviews());
        return "home";
    }

    @GetMapping("/{id}")
    public String viewById(@PathVariable("id") long id, Model model) {
        model.addAttribute("review", apiClient.getReviewById(id));
        return "viewReview";
    }

    @GetMapping("/add")
    public String addReview(Model model) {
        model.addAttribute("review", Review.builder().user(new User()).build());
        return "createReview";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute("review") Review review) {
        apiClient.create(review);
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String updateReview(@PathVariable("id") long id, Model model) {
        model.addAttribute("review", apiClient.getReviewById(id));
        return "updateReview";
    }

    @PostMapping("/{id}/update")
    public String updateReview(@ModelAttribute("review") Review review) {
        apiClient.update(review);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteReview(@PathVariable long id) {
        apiClient.removeById(id);
        return "redirect:/";
    }
}
