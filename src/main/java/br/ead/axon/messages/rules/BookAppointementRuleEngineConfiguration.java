package br.ead.axon.messages.rules;

import java.util.List;
import lombok.AllArgsConstructor;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BookAppointementRuleEngineConfiguration {

    @Bean
    public Rules rules() {
        Rules rules = new Rules();
        rules.register(new StartAfterEndRule());
        rules.register(new FutureAppointmentRule());
        // Register more rules here
        return rules;
    }

    @Bean
    public RulesEngine rulesEngine() {
        RulesEngineParameters parameters = new RulesEngineParameters();
        return new DefaultRulesEngine(parameters);
    }
}
