package com.almuhsin.saham.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tagihan_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagihanUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagihan_id", nullable = false)
    private Tagihan tagihan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @Column(name = "remaining_paiment")
    private Long remainingPaiment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "modified_by", nullable = false)
    private Integer modifiedBy;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "reasen_deleted")
    private String reasonDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }
}
