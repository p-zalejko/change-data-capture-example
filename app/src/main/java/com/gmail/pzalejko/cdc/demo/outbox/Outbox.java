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

// table schema is defined by debezium https://debezium.io/documentation/reference/stable/transformations/outbox-event-router.html
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

    // payload can be anything, String, byte[] etc.
    private String payload;

    @CreatedDate
    @Column(updatable = false)
    private Instant timestamp;

}