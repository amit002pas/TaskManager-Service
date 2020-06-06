package com.java.taskManager.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;
}
