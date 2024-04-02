package com.ecom.demo.service.Review;

import com.ecom.demo.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();


    Review addReview(Review review);
}
