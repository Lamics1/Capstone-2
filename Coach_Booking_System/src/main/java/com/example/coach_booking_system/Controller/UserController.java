package com.example.coach_booking_system.Controller;

import com.example.coach_booking_system.Api.ApiResponse;
import com.example.coach_booking_system.Model.User;
import com.example.coach_booking_system.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
final private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully "));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateUser(@PathVariable Integer id ,@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }
    //??
//    @GetMapping("/average/{gender}")
//    public ResponseEntity<?> giveMyAverageAgeByGender(@PathVariable String gender){
//        Double I = userService.giveMyAverageAgeByGender(gender);
//        return ResponseEntity.status(200).body(I);
//    }

}
