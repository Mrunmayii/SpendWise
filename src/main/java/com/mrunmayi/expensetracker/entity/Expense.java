package com.mrunmayi.expensetracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

//@Data
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name="exp_name", nullable=false)
    private String name;

    @Column(name="amount", nullable=false)
    private Double amount;

    private String category;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to Users table
    @JsonIgnore
    private User user;
}
