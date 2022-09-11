package com.gmail.pzalejko.cdc.demo.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxEventDispatcher {

    private final OutboxRepository repository;
    private final ObjectMapper mapper;

    @SneakyThrows
    @Transactional(propagation = Propagation.MANDATORY)
    public <T> void dispatch(@NonNull OutboxEvent<T> event) {
        T payload = event.payload();

        var outbox = new Outbox();
        outbox.setId(UUID.randomUUID());
        outbox.setAggregateId(event.id());
        outbox.setAggregateType(payload.getClass().getSimpleName());
        outbox.setType(payload.getClass().getSimpleName());
        outbox.setTimestamp(Timestamp.from(Instant.now()));
        outbox.setPayload(mapper.writeValueAsString(payload));

        repository.save(outbox);
    }

}
