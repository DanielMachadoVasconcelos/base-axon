package br.ead.axon.exceptions;

public class InvalidAppointmentPeriodException extends IllegalArgumentException {

    public InvalidAppointmentPeriodException() {
    }

    public InvalidAppointmentPeriodException(String message) {
        super(message);
    }

    public InvalidAppointmentPeriodException(String message, Throwable cause) {
        super(message, cause);
    }
}
