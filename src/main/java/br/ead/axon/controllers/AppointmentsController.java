package br.ead.axon.controllers;

import br.ead.axon.messages.commands.BookAppointmentCommand;
import br.ead.axon.messages.commands.CancelAppointmentCommand;
import br.ead.axon.messages.commands.ConfirmAppointmentCommand;
import br.ead.axon.messages.commands.RescheduleAppointmentCommand;
import br.ead.axon.model.querry.FindAllAppointmentQuery;
import br.ead.axon.model.entities.Appointment;
import br.ead.axon.model.requests.BookAppointmentRequest;
import br.ead.axon.model.requests.RescheduleAppointmentRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentsController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/book")
    public CompletableFuture<Void> bookAppointment(@RequestBody @Valid BookAppointmentRequest request) {
        var appointmentId = UUID.randomUUID().toString();
        log.info("Received a Http request for booking appointment. appointmentId = {}", appointmentId);
        return commandGateway.send(new BookAppointmentCommand(appointmentId,
                request.getStartAt(),
                request.getEndAt(),
                request.getLocation(),
                request.getParticipants()));
    }

    @PutMapping("{appointmentId}/confirm")
    public CompletableFuture<Void> confirmAppointment(@PathVariable @NotEmpty String appointmentId) {
        log.info("Received a Http request for confirm Appointment. appointmentId = {}", appointmentId);
        return commandGateway.send(new ConfirmAppointmentCommand(appointmentId));
    }

    @PutMapping("{appointmentId}/reschedule")
    public CompletableFuture<Void> rescheduleAppointment(@PathVariable @NotEmpty String appointmentId,
                                                         @RequestBody @Valid RescheduleAppointmentRequest request) {
        log.info("Received a Http request for reschedule Appointment. appointmentId = {}", appointmentId);
        return commandGateway.send(new RescheduleAppointmentCommand(appointmentId,
                request.getStartAt(),
                request.getEndAt()));
    }

    @PutMapping("{appointmentId}/cancel")
    public CompletableFuture<Void> cancelAppointment(@PathVariable @NotEmpty String appointmentId) {
        log.info("Received a Http request for cancel Appointment. appointmentId = {}", appointmentId);
        return commandGateway.send(new CancelAppointmentCommand(appointmentId));
    }

    @GetMapping("/")
    public CompletableFuture<List<Appointment>> findAllOrders() {
        log.info("Received a Http request for list all orders");
        return queryGateway.query(new FindAllAppointmentQuery(), ResponseTypes.multipleInstancesOf(Appointment.class));
    }
}

