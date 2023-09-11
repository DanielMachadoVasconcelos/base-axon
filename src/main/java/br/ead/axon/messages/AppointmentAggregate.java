package br.ead.axon.messages;

import br.ead.axon.exceptions.InvalidAppointmentPeriodException;
import br.ead.axon.messages.commands.BookAppointmentCommand;
import br.ead.axon.messages.commands.CancelAppointmentCommand;
import br.ead.axon.messages.commands.ConfirmAppointmentCommand;
import br.ead.axon.messages.commands.RescheduleAppointmentCommand;
import br.ead.axon.messages.events.AppointmentBookedEvent;
import br.ead.axon.messages.events.AppointmentCancelledEvent;
import br.ead.axon.messages.events.AppointmentConfirmedEvent;
import br.ead.axon.messages.events.AppointmentRescheduleEvent;
import br.ead.axon.model.api.Location;
import br.ead.axon.model.api.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Data
@Log4j2
@Aggregate
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentAggregate {

    @AggregateIdentifier
    private String appointmentId;
    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private Location location;
    private Set<Participant> participants;

    private ZonedDateTime appointmentConfirmedAt;
    private ZonedDateTime appointmentCancelledAt;

    @CommandHandler
    public AppointmentAggregate(BookAppointmentCommand command) {

        if(command.getStartAt().isBefore(ZonedDateTime.now().plusMinutes(15))) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment needs to happen in the future.");
        }

        if (command.getStartAt().isAfter(command.getEndAt())) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment can not start after it ends.");
        }

        if (command.getStartAt().equals(command.getEndAt())) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment do not have a duration.");
        }

        Duration duration = Duration.between(command.getStartAt(), command.getEndAt());
        if (duration.compareTo(Duration.ofMinutes(15l)) < 0) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment should last at minimum of 15 minutes.");
        }

        if (command.getLocation() == null) {
            throw new IllegalArgumentException("The appointment needs to happen in a known location.");
        }

        if (command.getParticipants() == null || command.getParticipants().isEmpty()) {
            throw new IllegalArgumentException("The appointment needs participants.");
        }

        if (command.getParticipants().size() < 2) {
            throw new IllegalArgumentException("The appointment needs at minimum of 2 participants. The provided number of participants was {}".formatted(
                    command.getParticipants().size()));
        }

        apply(new AppointmentBookedEvent(command.getAppointmentId(),
                command.getStartAt(),
                command.getEndAt(),
                command.getLocation(),
                command.getParticipants()));
    }

    @EventSourcingHandler
    public void on(AppointmentBookedEvent event) {
        this.appointmentId = event.getAppointmentId();
        this.startAt = event.getStartAt();
        this.endAt = event.getEndAt();
        this.location = event.getLocation();
        this.participants = event.getParticipants();
    }

    @CommandHandler
    public void handle(ConfirmAppointmentCommand command) {
        log.info("Received a ConfirmAppointmentCommand. AppointmentId = {}", command.getAppointmentId());
        apply(new AppointmentConfirmedEvent(command.getAppointmentId()));
    }

    @EventSourcingHandler
    public void on(AppointmentConfirmedEvent event) {
        this.appointmentConfirmedAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    @CommandHandler
    public void handle(CancelAppointmentCommand command) {
        log.info("Received a CancelAppointmentCommand. AppointmentId = {}", command.getAppointmentId());
        apply(new AppointmentCancelledEvent(command.getAppointmentId()));
    }

    @EventSourcingHandler
    public void on(AppointmentCancelledEvent event) {
        this.appointmentCancelledAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    @CommandHandler
    public void handle(RescheduleAppointmentCommand command) {
        log.info("Received a RescheduleAppointmentCommand. AppointmentId = {}", command.getAppointmentId());

        if(command.getStartAt().isBefore(ZonedDateTime.now().plusMinutes(15))) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment needs to happen in the future.");
        }

        if (command.getStartAt().isAfter(command.getEndAt())) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment can not start after it ends.");
        }

        if (command.getStartAt().equals(command.getEndAt())) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment do not have a duration.");
        }

        Duration duration = Duration.between(command.getStartAt(), command.getEndAt());
        if (duration.compareTo(Duration.ofMinutes(15l)) < 0) {
            throw new InvalidAppointmentPeriodException("Period of date time is invalid. Appointment should last at minimum of 15 minutes.");
        }

        apply(new AppointmentRescheduleEvent(command.getAppointmentId(),
                command.getStartAt(),
                command.getEndAt()));
    }

    @EventSourcingHandler
    public void on(AppointmentRescheduleEvent event) {
        this.startAt = event.getStartAt();
        this.endAt = event.getEndAt();
    }
}
