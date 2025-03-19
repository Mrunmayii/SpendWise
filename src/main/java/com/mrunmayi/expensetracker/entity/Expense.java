package com.mrunmayi.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
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
}
