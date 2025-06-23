package pe.edu.upc.center.platform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Dashboard;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateDashboardCommand;
import pe.edu.upc.center.platform.profiles.domain.services.DashboardService;

import java.util.Optional;

@Service
public class CreateDashboardCommandService {
    private final DashboardService dashboardService;

    public CreateDashboardCommandService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    public Optional<Dashboard> handle(CreateDashboardCommand command) {
        return dashboardService.createDashboard(command);
    }
}