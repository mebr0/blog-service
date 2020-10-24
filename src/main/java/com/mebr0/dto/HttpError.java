package com.mebr0.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Schema(name = "HttpError", description = "Http error with message")
public class HttpError {

    @NonNull
    @Schema(name = "message", description = "Message of error", example = "http error message")
    private String message;

    public static HttpError of(String message) {
        return new HttpError(message);
    }
}
