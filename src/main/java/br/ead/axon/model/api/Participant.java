package br.ead.axon.model.api;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeOfParticipant")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PractitionerParticipant.class, name = "practitioner"),
        @JsonSubTypes.Type(value = PatientParticipant.class, name = "patient"),
})
public interface Participant {

}
