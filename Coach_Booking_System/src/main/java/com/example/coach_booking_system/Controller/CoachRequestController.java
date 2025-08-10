package com.example.coach_booking_system.Controller;

import com.example.coach_booking_system.Api.ApiResponse;
import com.example.coach_booking_system.Model.CoachRequest;
import com.example.coach_booking_system.Service.CoachRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/coach-request")

public class CoachRequestController {
        private final CoachRequestService coachRequestService;

        @GetMapping("/get")
        public ResponseEntity<?> getAll() {
            return ResponseEntity.status(200).body(coachRequestService.getAllRequests());
        }


        @PostMapping("/add")
        public ResponseEntity<?> add(@Valid @RequestBody CoachRequest coachRequest, Errors errors) {
            if (errors.hasErrors()) {
                return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
            }
            coachRequestService.addRequest(coachRequest);
            return ResponseEntity.status(200).body(new ApiResponse("Coach request added successfully"));
        }

        // update
        @PutMapping("/update/{id}")
        public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody CoachRequest coachRequest, Errors errors) {
            if (errors.hasErrors()) {
                return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
            }
            coachRequestService.updateRequest(id, coachRequest);
            return ResponseEntity.status(200).body(new ApiResponse("Coach request updated successfully"));
        }


        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Integer id) {
            coachRequestService.deleteRequest(id);
            return ResponseEntity.status(200).body(new ApiResponse("Coach request deleted successfully"));
        }

//extra end point ( 7 )
        @PutMapping("/change-status-accepted/{id}/{status}")
        public ResponseEntity<?> changeStatusAccepted(@PathVariable Integer id, @PathVariable String status) {
            coachRequestService.changeStatusAccepted(id, status);
            return ResponseEntity.status(200).body(new ApiResponse("Coach request status updated successfully"));
        }

//extra end point ( 6 )
        @GetMapping("/coach/{coachId}/status/{status}")
        public ResponseEntity<?> getByCoachAndStatus(@PathVariable Integer coachId, @PathVariable String status) {
            return ResponseEntity.status(200).body(coachRequestService.getByCoachAndStatus(coachId, status));
        }

//extra end point ( 11 )
    @PutMapping("/change-status-rejected/{id}/{status}")
    public ResponseEntity<?> changeStatusToRejected(@PathVariable Integer id, @PathVariable String status) {
        coachRequestService.changeStatusToRejected(id, status);
        return ResponseEntity.status(200).body(new ApiResponse("Coach request status updated successfully"));
    }
// extra end point ( 13 )
    @GetMapping("/rejected/{userId}")
    public ResponseEntity<?> getRejectedRequests(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(coachRequestService.getRejectedRequestsByUser(userId));

    }
    }
