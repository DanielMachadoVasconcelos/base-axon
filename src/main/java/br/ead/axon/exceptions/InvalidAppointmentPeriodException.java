package br.ead.axon.exceptions;

import org.springframework.http.ProblemDetail;

public class InvalidAppointmentPeriodException extends IllegalArgumentException {

    private ProblemDetail problemDetail;

    public InvalidAppointmentPeriodException(ProblemDetail problemDetail) {
        this.problemDetail = problemDetail;
    }

    public InvalidAppointmentPeriodException(String s) {
        super(s);
    }
}
