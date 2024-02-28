package com.example.BarokTest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "wallet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int walletId;

    @Min(value = 0, message = "The balance can not be smaller zero")
    private long balance;

    @Column(name = "user_id")
    @NotNull
    private int userId;
//man basham mizaram "created_dt"

    @Column(name = "created_at")
    private Timestamp createdat;
//man basham mizaram "updata_dt"
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
