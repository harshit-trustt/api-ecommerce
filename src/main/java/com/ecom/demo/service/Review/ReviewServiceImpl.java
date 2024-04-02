package com.ecom.demo.service.Review;

import com.ecom.demo.entity.Review;
import com.ecom.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }


}

