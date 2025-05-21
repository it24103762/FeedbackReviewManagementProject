package com.example.FeedbackReviewApp.model;

public class PublicReview extends Review{



    public PublicReview(String reviewId, String vendorName, String reviewerName, String feedback, int rating) {
        super(reviewId, vendorName, reviewerName, feedback, rating, false);
    }

    @Override
    public String displayReview() {
        return "Public Review for " + getVendorName() + " by " + getReviewerName() + ": " + getFeedback() + " (Rating: " + getRating() + ")";
    }


}
