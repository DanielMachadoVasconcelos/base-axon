package br.ead.axon.model.entities;

import br.ead.axon.model.api.Location;
import br.ead.axon.model.api.Participant;
import br.ead.axon.model.entities.converters.CustomZonedDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;

import java.time.ZonedDateTime;
import java.util.Set;

@With
@Data
@AllArgsConstructor
@Document(indexName = "appointments")
public final class Appointment {

    @Id
    private String appointmentId;

    @ValueConverter(CustomZonedDateTimeConverter.class)
    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSX||uuuu-MM-dd'T'HH:mm:ss.SSS", format = {})
    private ZonedDateTime startAt;

    @ValueConverter(CustomZonedDateTimeConverter.class)
    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSX||uuuu-MM-dd'T'HH:mm:ss.SSS", format = {})
    private ZonedDateTime endAt;

    private Location location;

    private Set<Participant> participants;

    private AppointmentStatus appointmentStatus;
}
