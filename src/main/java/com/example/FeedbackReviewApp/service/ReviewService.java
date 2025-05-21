package com.example.FeedbackReviewApp.service;

import org.springframework.stereotype.Service;
import com.example.FeedbackReviewApp.model.PublicReview;
import com.example.FeedbackReviewApp.model.Review;
import com.example.FeedbackReviewApp.model.VerifiedReview;
import com.example.FeedbackReviewApp.util.FileHandler;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.LinkedList;



@Service
public class ReviewService {

    private final FileHandler fileHandler;

    public ReviewService() {
        this.fileHandler = new FileHandler();
    }

    // Linked List Node for reviews
    private static class ReviewNode {
        Review review;
        ReviewNode next;

        ReviewNode(Review review) {
            this.review = review;
            this.next = null;
        }
    }

    // Convert LinkedList to List for API response
    private List<Review> linkedListToList(ReviewNode head) {
        List<Review> result = new LinkedList<>();
        ReviewNode current = head;
        while (current != null) {
            result.add(current.review);
            current = current.next;
        }
        return result;
    }

    // Bubble Sort by rating (descending order)
    private ReviewNode bubbleSortByRating(ReviewNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        boolean swapped;
        ReviewNode dummy = new ReviewNode(null);
        dummy.next = head;
        do {
            swapped = false;
            ReviewNode current = dummy.next;
            ReviewNode prev = dummy;

            while (current != null && current.next != null) {
                if (current.review.getRating() < current.next.review.getRating()) {
                    // Swap nodes
                    prev.next = current.next;
                    current.next = current.next.next;
                    prev.next.next = current;
                    swapped = true;
                }
                prev = prev.next;
                current = prev.next;
            }
        } while (swapped);
        return dummy.next;
    }

    public void createReview(String vendorName, String reviewerName, String feedback, int rating, boolean isVerified) throws IOException {
        String reviewId = UUID.randomUUID().toString();
        Review review = isVerified ?
                new VerifiedReview(reviewId, vendorName, reviewerName, feedback, rating) :
                new PublicReview(reviewId, vendorName, reviewerName, feedback, rating);
        fileHandler.saveReview(review);
    }

    public List<Review> getAllReviews() throws IOException {
        List<Review> reviews = fileHandler.readReviews();
        // Convert List to Linked List
        ReviewNode head = null;
        ReviewNode tail = null;
        for (Review review : reviews) {
            ReviewNode newNode = new ReviewNode(review);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        // Sort using Bubble Sort
        head = bubbleSortByRating(head);
        // Convert back to List
        return linkedListToList(head);
    }

    public void updateReview(String reviewId, String vendorName, String feedback, int rating) throws IOException {
        List<Review> reviews = fileHandler.readReviews();
        for (Review review : reviews) {
            if (review.getReviewId().equals(reviewId)) {
                review.setVendorName(vendorName);
                review.setFeedback(feedback);
                review.setRating(rating);
                fileHandler.updateReview(review);
                break;
            }
        }
    }


    public void deleteReview(String reviewId) throws IOException {
        fileHandler.deleteReview(reviewId);
    }






}
