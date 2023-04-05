package com.project.reviewclient.service;

import com.project.reviewclient.model.Review;

import java.util.List;

public interface ReviewApiClient {
    Review create(Review review);

    Review update(Review review);

    Review getReviewById(long id);

    void removeById(long id);

    List<Review> getAllReviews();
}
