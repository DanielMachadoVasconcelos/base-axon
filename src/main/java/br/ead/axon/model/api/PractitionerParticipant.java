package br.ead.axon.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="practitioner")
public class PractitionerParticipant implements Participant {

    private String id;
    private String typeOfParticipant = "practitioner";

    public PractitionerParticipant(String id) {
        this.id = id;
    }
}
