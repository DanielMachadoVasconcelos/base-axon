package br.ead.axon.model.api;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="clinician")
@DiscriminatorValue("clinician")
public class ClinicianParticipant extends Participant {

    private String id;
}
