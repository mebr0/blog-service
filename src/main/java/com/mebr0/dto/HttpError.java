package com.mebr0.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@RequiredArgsConstructor(staticName = "of")
@Schema(name = "HttpError", description = "Http error with message")
public class HttpError {

    @NonNull
    @Schema(name = "message", description = "Message of error", required = true, example = "error message")
    private final String message;
}
