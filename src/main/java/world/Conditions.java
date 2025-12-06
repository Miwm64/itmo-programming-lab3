package world;

import java.time.LocalDateTime;
import java.util.Objects;

public record Conditions(Weather weather, LocalDateTime time, Effect effect) {
    public Conditions {
        Objects.requireNonNull(weather);
        Objects.requireNonNull(time);
        Objects.requireNonNull(effect);
    }
}
