package com.example.FeedbackReviewApp.model;

public class VerifiedReview extends Review{

    public VerifiedReview(String reviewId, String vendorName, String reviewerName, String feedback, int rating) {
        super(reviewId, vendorName, reviewerName, feedback, rating, true);
    }

    @Override
    public String displayReview() {
        return "Verified Review for " + getVendorName() + " by " + getReviewerName() + ": " + getFeedback() + " (Rating: " + getRating() + ") [Verified]";
    }

}
