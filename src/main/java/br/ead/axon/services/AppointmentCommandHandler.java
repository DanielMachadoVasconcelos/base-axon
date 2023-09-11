package br.ead.axon.services;

import br.ead.axon.messages.commands.BookAppointmentCommand;
import br.ead.axon.messages.commands.CancelAppointmentCommand;
import br.ead.axon.messages.commands.ConfirmAppointmentCommand;
import br.ead.axon.messages.commands.RescheduleAppointmentCommand;
import br.ead.axon.model.entities.Appointment;
import br.ead.axon.model.querry.FindAllAppointmentQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentCommandHandler {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public String dispatch(BookAppointmentCommand command) {
        log.info("Dispatching the BookAppointmentCommand [{}]", command);
        return commandGateway.sendAndWait(command, 5, TimeUnit.SECONDS);
    }

    public String dispatch(ConfirmAppointmentCommand command) {
        log.info("Dispatching the ConfirmAppointmentCommand [{}]", command);
        return commandGateway.sendAndWait(command, 5, TimeUnit.SECONDS);
    }

    public String dispatch(RescheduleAppointmentCommand command) {
        log.info("Dispatching the RescheduleAppointmentCommand [{}]", command);
        return commandGateway.sendAndWait(command, 5, TimeUnit.SECONDS);
    }

    public String dispatch(CancelAppointmentCommand command) {
        log.info("Dispatching the CancelAppointmentCommand [{}]", command);
        return commandGateway.sendAndWait(command, 5, TimeUnit.SECONDS);
    }

    @SneakyThrows
    public List<Appointment> dispatch(FindAllAppointmentQuery query) {
        ResponseType<List<Appointment>> responseType = ResponseTypes.multipleInstancesOf(Appointment.class);
        return queryGateway.query(query, responseType).get(5, TimeUnit.SECONDS);
    }
}
