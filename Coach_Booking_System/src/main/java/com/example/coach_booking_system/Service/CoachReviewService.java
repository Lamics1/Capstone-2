package com.example.coach_booking_system.Service;

import com.example.coach_booking_system.Api.ApiException;
import com.example.coach_booking_system.Model.Coach;
import com.example.coach_booking_system.Model.CoachReviews;
import com.example.coach_booking_system.Model.User;
import com.example.coach_booking_system.Repository.CoachRepository;
import com.example.coach_booking_system.Repository.CoachReviewRepository;
import com.example.coach_booking_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachReviewService {

    final private CoachReviewRepository coachReviewRepository;
    final private CoachRepository coachRepository;
    final private UserRepository userRepository;
   // final private SportRepository sportRepository;


    public List<CoachReviews> getAllReviews() {
        return coachReviewRepository.findAll();
    }

    //extra end point ( 10 )

    public void addReview(CoachReviews coachReview) {

    User user = userRepository.findUserById(coachReview.getUserId());
    if (user == null) {
        throw new ApiException("User not found");
    }

    Coach coach = coachRepository.findCoachById(coachReview.getCoachId());
    if (coach == null) {
        throw new ApiException("Coach not found");
    }
    // check if user not review 2
    CoachReviews existingReview = coachReviewRepository.findCoachReviewsByUserIdAndCoachId(coachReview.getUserId(), coachReview.getCoachId());

    if (existingReview != null) {
        throw new ApiException("You have already reviewed this coach");
    }

    //check if the status is accepted

    coachReviewRepository.save(coachReview);
}

    public void updateReview(Integer id, CoachReviews coachReview) {
        CoachReviews oldReview = coachReviewRepository.findCoachReviewsById(id);
        if (oldReview == null) {
            throw new ApiException("Review not found");
        }

        User user = userRepository.findUserById(coachReview.getUserId());
        if (user == null) {
            throw new ApiException("User not found");
        }

        Coach coach = coachRepository.findCoachById(coachReview.getCoachId());
        if (coach == null) {
            throw new ApiException("Coach not found");
        }

        oldReview.setRating(coachReview.getRating());
        oldReview.setComment(coachReview.getComment());
        oldReview.setUserId(coachReview.getUserId());
        oldReview.setCoachId(coachReview.getCoachId());

        coachReviewRepository.save(oldReview);
    }

    public void deleteReview(Integer id) {
        CoachReviews review = coachReviewRepository.findCoachReviewsById(id);
        if (review == null) {
            throw new ApiException("Review not found");
        }
        coachReviewRepository.delete(review);
    }

}
