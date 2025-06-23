package pe.edu.upc.center.platform.profiles.interfaces.rest.transform;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Dashboard;
import pe.edu.upc.center.platform.profiles.interfaces.rest.resources.DashboardResource;

public class DashboardResourceAssembler {
    public static DashboardResource toResourceFromEntity(Dashboard dashboard) {
        return new DashboardResource(
                dashboard.getId(),
                dashboard.getActiveStudents(),
                dashboard.getScheduledSessions(),
                dashboard.getConsultationHours(),
                dashboard.getAverageRating(),
                dashboard.getTotalRatings(),
                dashboard.getLastUpdate()
        );
    }
}