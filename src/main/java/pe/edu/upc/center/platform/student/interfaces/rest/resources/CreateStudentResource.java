package pe.edu.upc.center.platform.student.interfaces.rest.resources;

public record CreateStudentResource(String name, int age, String address,
                                    Long programId, String startPeriod) {
}
