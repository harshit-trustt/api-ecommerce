package com.ecom.demo.controller;


import com.ecom.demo.entity.Review;
import com.ecom.demo.service.Review.ReviewService;
import com.ecom.demo.service.Review.ReviewServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Get all Reviews", description = "Returns all Reviews")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found - The Review was not found")
    })
    @GetMapping
    public List<Review> getAllReview()
    {
        return reviewService.getAll();
    }


    @Operation(summary = "insert the review", description = "Inserts the review")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully Inserted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Couldn't insert the review")
    })
    @PostMapping
    public Review addReview(@RequestBody Review review)
    {
        return reviewService.addReview(review);
    }

}
