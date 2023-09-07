package br.ead.axon.service;

import br.ead.axon.exceptions.InvalidAppointmentPeriodException;
import br.ead.axon.messages.AppointmentAggregate;
import br.ead.axon.messages.commands.BookAppointmentCommand;
import br.ead.axon.model.api.Location;
import br.ead.axon.model.api.Participant;
import br.ead.axon.model.api.PatientParticipant;
import br.ead.axon.model.api.PhysicalLocation;
import br.ead.axon.model.api.ClinicianParticipant;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

public class BookAppointmentTest {

    private FixtureConfiguration<AppointmentAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(AppointmentAggregate.class);
    }

    @Test
    void shouldNotAllowToBookAppointmentWhenStartTimeIsInThePast() {
        var appointmentId = UUID.randomUUID().toString();
        var startAtInPast = ZonedDateTime.now().minusHours(1);
        var endAt = startAtInPast.plusMinutes(15);
        Set<Participant> participants = Set.of(new ClinicianParticipant("1"), new PatientParticipant("2"));
        Location location = new PhysicalLocation();
        fixture.givenNoPriorActivity()
                .when(new BookAppointmentCommand(appointmentId, startAtInPast, endAt, location, participants))
                .expectException(InvalidAppointmentPeriodException.class);
    }

    @Test
    void shouldNotAllowAppointmentsWhenStartAndEndTimeAreTheSame() {
        var appointmentId = UUID.randomUUID().toString();
        var oneHourInFuture = ZonedDateTime.now().plusHours(1);
        var startAt = oneHourInFuture;
        var endAt = oneHourInFuture;
        Set<Participant> participants = Set.of(new ClinicianParticipant("1"), new PatientParticipant("2"));
        Location location = new PhysicalLocation();
        fixture.givenNoPriorActivity()
                .when(new BookAppointmentCommand(appointmentId, startAt, endAt, location, participants))
                .expectException(InvalidAppointmentPeriodException.class);
    }
}
