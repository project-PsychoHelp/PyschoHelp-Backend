package pe.edu.upc.center.platform.dashboard.domain.model.valueobjects;

import pe.edu.upc.center.platform.test.domain.model.valueobjects.CategoriesExam;

public enum TypesExam {

    PREMIUN(1),
    NOPREMIUN(2);

    private final int value;

    TypesExam(int value) {
        this.value = value;
    }

    public static TypesExam fromName(String name) {
        for (TypesExam type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type type: " + name);
    }


}
