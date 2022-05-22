package br.ead.axon.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@Document(indexName="digital-location")
public class DigitalLocation implements Location {

    private String url;
    private String typeOfLocation = "digital";

    public DigitalLocation(String url) {
        this.url = url;
    }
}
