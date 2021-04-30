package ru.ds.education.exercise.cbr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Component
public class CurrencyModel {
    @JsonProperty("Currency")
    private String currencyModelName;

    @JsonProperty("Curs")
    private double currencyModelValue;

}
