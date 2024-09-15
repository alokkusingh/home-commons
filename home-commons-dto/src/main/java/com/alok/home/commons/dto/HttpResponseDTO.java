package com.alok.home.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HttpResponseDTO {

    private Status status;
    private Object data;
    private String message;

    public enum Status {
        SUCCESS,
        FAILED
    }
}
