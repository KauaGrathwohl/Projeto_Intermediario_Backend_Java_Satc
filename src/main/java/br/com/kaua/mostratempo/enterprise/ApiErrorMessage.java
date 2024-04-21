package br.com.kaua.mostratempo.enterprise;

import java.time.Instant;

public record ApiErrorMessage(
        Instant timestamp,
        int status,
        String message
) {
}
