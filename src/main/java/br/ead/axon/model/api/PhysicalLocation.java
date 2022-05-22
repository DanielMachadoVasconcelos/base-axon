package br.ead.axon.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="physical-location")
public class PhysicalLocation implements Location {

    private String typeOfLocation = "physical";
    private String address;

    public PhysicalLocation(String address) {
        this.address = address;
    }
}
