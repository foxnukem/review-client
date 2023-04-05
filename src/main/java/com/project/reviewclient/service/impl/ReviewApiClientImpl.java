package com.project.reviewclient.service.impl;

import com.project.reviewclient.model.Review;
import com.project.reviewclient.service.ReviewApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewApiClientImpl implements ReviewApiClient {
    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String serverUrl;

    @Override
    public Review create(Review review) {
        var createdReview = restTemplate.postForEntity(serverUrl, review, Review.class);
        return createdReview.getBody();
    }

    @Override
    public Review update(Review review) {
        var updatedReview = restTemplate.exchange(RequestEntity.put(serverUrl + "/%d".formatted(review.getId())).body(review), Review.class);
        return updatedReview.getBody();
    }

    @Override
    public Review getReviewById(long id) {
        var fetchedReviewById = restTemplate.exchange(RequestEntity.get(serverUrl + "/%d".formatted(id)).build(), Review.class);
        return fetchedReviewById.getBody();
    }

    @Override
    public void removeById(long id) {
        restTemplate.delete(serverUrl + "/%d".formatted(id));
    }

    @Override
    public List<Review> getAllReviews() {
        var fetchedReviews = restTemplate.exchange(RequestEntity.get(serverUrl).build(), new ParameterizedTypeReference<List<Review>>() {
        });
        return fetchedReviews.getBody();
    }
}
