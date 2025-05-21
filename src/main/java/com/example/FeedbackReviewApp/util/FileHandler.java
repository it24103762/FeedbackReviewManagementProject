package com.example.FeedbackReviewApp.util;

import com.example.FeedbackReviewApp.model.Review;
import com.example.FeedbackReviewApp.model.PublicReview;
import com.example.FeedbackReviewApp.model.VerifiedReview;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    private static final String FILE_PATH = "src/main/resources/data/reviews.txt";

    public void saveReview(Review review) throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(review.getReviewId() + "|" +
                    review.getVendorName() + "|" +
                    review.getReviewerName() + "|" +
                    review.getFeedback() + "|" +
                    review.getRating() + "|" +
                    review.isVerified());
            writer.newLine();
        }
    }

    public List<Review> readReviews() throws IOException {
        List<Review> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return reviews;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String reviewId = parts[0];
                    String vendorName = parts[1];
                    String reviewerName = parts[2];
                    String feedback = parts[3];
                    int rating = Integer.parseInt(parts[4]);
                    boolean isVerified = Boolean.parseBoolean(parts[5]);
                    Review review = isVerified ?
                            new VerifiedReview(reviewId, vendorName, reviewerName, feedback, rating) :
                            new PublicReview(reviewId, vendorName, reviewerName, feedback, rating);
                    reviews.add(review);
                }
            }
        }
        return reviews;
    }


    public void updateReview(Review updatedReview) throws IOException {
        List<Review> reviews = readReviews();
        File file = new File(FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Review review : reviews) {
                if (review.getReviewId().equals(updatedReview.getReviewId())) {
                    writer.write(updatedReview.getReviewId() + "|" +
                            updatedReview.getVendorName() + "|" +
                            updatedReview.getReviewerName() + "|" +
                            updatedReview.getFeedback() + "|" +
                            updatedReview.getRating() + "|" +
                            updatedReview.isVerified());
                } else {
                    writer.write(review.getReviewId() + "|" +
                            review.getVendorName() + "|" +
                            review.getReviewerName() + "|" +
                            review.getFeedback() + "|" +
                            review.getRating() + "|" +
                            review.isVerified());
                }
                writer.newLine();
            }
        }
    }


    public void deleteReview(String reviewId) throws IOException {
        List<Review> reviews = readReviews();
        File file = new File(FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Review review : reviews) {
                if (!review.getReviewId().equals(reviewId)) {
                    writer.write(review.getReviewId() + "|" +
                            review.getVendorName() + "|" +
                            review.getReviewerName() + "|" +
                            review.getFeedback() + "|" +
                            review.getRating() + "|" +
                            review.isVerified());
                    writer.newLine();
                }
            }
        }
    }




}
