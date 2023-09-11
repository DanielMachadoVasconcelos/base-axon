package br.ead.axon.messages.rules;

import org.jeasy.rules.api.Rule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentRulesConfiguration {

    @Bean
    public Rule startAfterEndRule() {
        return new StartAfterEndRule();
    }

    @Bean
    public Rule futureAppointmentRule() {
        return new FutureAppointmentRule();
    }
}
