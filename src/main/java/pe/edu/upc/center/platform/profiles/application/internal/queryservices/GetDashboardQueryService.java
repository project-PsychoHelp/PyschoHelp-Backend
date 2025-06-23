package pe.edu.upc.center.platform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Dashboard;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetDashboardQuery;
import pe.edu.upc.center.platform.profiles.domain.services.DashboardService;

import java.util.Optional;

@Service
public class GetDashboardQueryService {
    private final DashboardService dashboardService;

    public GetDashboardQueryService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    public Optional<Dashboard> handle(GetDashboardQuery query) {
        return dashboardService.getDashboard();
    }
}