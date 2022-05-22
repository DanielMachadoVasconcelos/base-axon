package br.ead.axon.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="patient")
public class PatientParticipant implements Participant {

    private String id;
    private String typeOfParticipant = "patient";

    public PatientParticipant(String id) {
        this.id = id;
    }
}
