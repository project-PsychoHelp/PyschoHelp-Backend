package pe.edu.upc.center.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.*;

import java.util.List;

@Entity

public class Progress {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer overallPercentage;

    @ElementCollection
    private List<String> tests;

    public Progress(){}

    public Progress(Integer overallPercentage, List<String> tests) {
        if (overallPercentage == null) {
            throw new IllegalArgumentException("overallPercentage cannot be null");
        }
        if (tests == null) {
            throw new IllegalArgumentException("tests cannot be null");
        }
        if (overallPercentage < 0 || overallPercentage > 100) {
            throw new IllegalArgumentException("overallPercentage must be between 0 and 100");
        }
        this.overallPercentage = overallPercentage;
        this.tests = tests;
    }


}