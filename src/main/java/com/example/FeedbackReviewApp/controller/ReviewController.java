package com.example.FeedbackReviewApp.controller;

import com.example.FeedbackReviewApp.dto.ReviewRequest;
import com.example.FeedbackReviewApp.model.Review;
import com.example.FeedbackReviewApp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewRequest request) throws IOException {
        reviewService.createReview(request.getVendorName(), request.getReviewerName(),
                request.getFeedback(), request.getRating(), request.isVerified());
        return ResponseEntity.ok("Review created successfully");
    }


    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() throws IOException {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable String reviewId, @RequestBody ReviewRequest request) throws IOException {
        reviewService.updateReview(reviewId, request.getVendorName(), request.getFeedback(), request.getRating());
        return ResponseEntity.ok("Review updated successfully");
    }



    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId) throws IOException {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }







}
