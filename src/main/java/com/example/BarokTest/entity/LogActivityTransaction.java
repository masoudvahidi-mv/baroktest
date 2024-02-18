package com.example.BarokTest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "logactivity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LogActivityTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    @NotNull
    private int userId;

    @NotNull
    private long amount;

    @Column(name = "type",columnDefinition = "TEXT")
    @NotNull
    private String type;

    @Column(name = "transaction_id",columnDefinition = "TEXT")
    @NotNull
    private String transactionId;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;
}
