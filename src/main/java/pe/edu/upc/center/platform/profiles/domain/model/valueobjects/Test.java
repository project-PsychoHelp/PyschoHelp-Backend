package pe.edu.upc.center.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable

public record Test(String name, String status, String completionDate, String estimatedTime) {
    public Test() {
        this("", "", null, null);
    }

    public Test {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status cannot be null or blank");
        }
    }
}