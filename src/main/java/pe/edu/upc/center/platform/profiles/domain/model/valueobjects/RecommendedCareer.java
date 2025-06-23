package pe.edu.upc.center.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;


@Embeddable

public class RecommendedCareer {
    private String name;
    private String description;
    private Integer compatibilityPercentage;

    public RecommendedCareer(){}
    public RecommendedCareer(String name, String description, Integer compatibilityPercentage) {
        this.name = name;
        this.description = description;
        this.compatibilityPercentage = compatibilityPercentage;
    }
}