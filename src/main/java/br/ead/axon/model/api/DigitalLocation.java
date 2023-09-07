package br.ead.axon.model.api;

import br.ead.axon.model.entities.Appointment;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="digital_location")
@DiscriminatorValue("digital")
public class DigitalLocation extends Location {

    @Column(name = "url")
    private String url;

}
