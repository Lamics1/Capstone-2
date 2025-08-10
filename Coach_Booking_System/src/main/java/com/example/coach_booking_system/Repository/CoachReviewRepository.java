package com.example.coach_booking_system.Repository;

import com.example.coach_booking_system.Model.CoachReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachReviewRepository extends JpaRepository<CoachReviews,Integer> {

    CoachReviews findCoachReviewsByUserIdAndCoachId(Integer userId, Integer coachId);

    CoachReviews findCoachReviewsById(Integer id);

    @Query("select cr from CoachReviews cr where cr.id = ?1")
    CoachReviews giveMeReviewById(Integer id);

    List<CoachReviews> findByCoachId(Integer coachId);

}
