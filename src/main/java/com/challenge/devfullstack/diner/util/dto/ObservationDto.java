package com.challenge.devfullstack.diner.util.dto;

import com.challenge.devfullstack.diner.model.Observation;
import jakarta.validation.constraints.Size;

public record ObservationDto(
        @Size(max = 100, message = ErrorFieldsMessages.MAX_SIZE)
        String message
) {
        public ObservationDto(Observation observation) {
                this(observation.getObservation());
        }
}
