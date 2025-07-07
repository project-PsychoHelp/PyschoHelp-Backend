package pe.edu.upc.center.platform.test.domain.model.valueobjects;

public enum CategoriesExam {
    PSYCHOLOGICAL(1),
    MEDICAL(2),
    EDUCATIONAL(3),
    SOCIAL(4);

    private final int value;

    CategoriesExam(int value) {
        this.value = value;
    }

    public static CategoriesExam fromName(String name) {
        for (CategoriesExam type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid category type: " + name);
    }
}
