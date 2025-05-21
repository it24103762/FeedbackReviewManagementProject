package com.example.FeedbackReviewApp.model;

public abstract class Review {
    private String reviewId;
    private String vendorName;
    private String reviewerName;
    private String feedback;
    private int rating;
    private boolean isVerified;


    public Review(String reviewId, String vendorName, String reviewerName, String feedback, int rating, boolean isVerified) {
        this.reviewId = reviewId;
        this.vendorName = vendorName;
        this.reviewerName = reviewerName;
        this.feedback = feedback;
        this.rating = rating;
        this.isVerified = isVerified;
    }


    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }


    public abstract String displayReview();




















}


