package com.example.coach_booking_system.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Pattern(regexp = "pending|completed")
//    @Column(columnDefinition = "varchar(10) not null default 'pending'")
//    private String status;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "coachId must be not null")
    private Integer coachId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "userId must be not null")
    private Integer userId;

    @Column(columnDefinition = "date not null")
    @NotNull(message = "Start date must not be null")
    private LocalDate startTime;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
