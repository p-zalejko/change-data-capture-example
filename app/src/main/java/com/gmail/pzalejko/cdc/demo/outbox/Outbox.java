package com.gmail.pzalejko.cdc.demo.outbox;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Immutable
@Data
@NoArgsConstructor
class Outbox {

    @Id
    private UUID id;

    @Column(nullable = false, name = "aggregatetype")
    private String aggregateType;

    @Column(nullable = false, name = "aggregateid")
    private String aggregateId;

    @Column(nullable = false)
    private String type;

//    @Lob
//    @Column(nullable = false)
//    private byte[] payload;
    private String payload;

    @CreatedDate
    @Column(updatable = false)
    private Instant timestamp;

}