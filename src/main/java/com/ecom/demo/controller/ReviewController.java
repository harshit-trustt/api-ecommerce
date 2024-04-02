package com.ecom.demo.controller;


import com.ecom.demo.entity.Review;
import com.ecom.demo.service.Review.ReviewService;
import com.ecom.demo.service.Review.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReview()
    {
        return reviewService.getAll();
    }


    @PostMapping
    public Review addReview(@RequestBody Review review)
    {
        return reviewService.addReview(review);
    }

}
