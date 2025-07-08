package pe.edu.upc.center.platform.dashboard.domain.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.TypesExam;
import pe.edu.upc.center.platform.test.domain.model.entities.CategoryExam;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CategoriesExam;

@Entity
@Table(name = "types_exam")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class TypeExam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private TypesExam name;


    public TypeExam(TypesExam name) {
        this.name = name;
    }
    public String getStringName() {
        return name.name();
    }
    public static TypeExam getDefaultTypeExam() {
        return new TypeExam(TypesExam.PREMIUN);
    }
    public static TypeExam toTypeExamFromName(String name) {
        return new TypeExam(TypesExam.valueOf(name));
    }

}
