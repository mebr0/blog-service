package com.mebr0.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class HttpError {

    @NonNull
    private String message;

    public static HttpError of(String message) {
        return new HttpError(message);
    }
}
