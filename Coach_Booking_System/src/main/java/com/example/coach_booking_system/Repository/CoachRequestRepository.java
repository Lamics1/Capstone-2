package com.example.coach_booking_system.Repository;

import com.example.coach_booking_system.Model.CoachRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoachRequestRepository extends JpaRepository<CoachRequest,Integer> {

    CoachRequest findCoachRequestById(Integer id);

    @Query("SELECT cr from CoachRequest cr where cr.coachId=?1 and cr.status =?2")
    List<CoachRequest> getByCoachAndStatus(Integer CoachId,String status);

    List<CoachRequest> findCoachRequestsByUserIdAndStatusIgnoreCase(Integer userId, String status);
}
