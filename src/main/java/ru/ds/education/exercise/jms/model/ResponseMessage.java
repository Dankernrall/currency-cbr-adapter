package ru.ds.education.exercise.jms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.ds.education.exercise.cbr.model.CurrencyModel;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseMessage {
    @JsonProperty("onDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate onDate;

    @JsonProperty("rates")
    private List<CurrencyModel> currencyList;
}