package pe.edu.upc.center.platform.profiles.domain.services;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Dashboard;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateDashboardCommand;

import java.util.Optional;

public interface DashboardService {
    Optional<Dashboard> createDashboard(CreateDashboardCommand command);
    Optional<Dashboard> getDashboard();
}