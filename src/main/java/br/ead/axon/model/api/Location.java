package br.ead.axon.model.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeOfLocation")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PhysicalLocation.class, name = "physical"),
        @JsonSubTypes.Type(value = DigitalLocation.class, name = "digital"),
})
public interface Location {

}
