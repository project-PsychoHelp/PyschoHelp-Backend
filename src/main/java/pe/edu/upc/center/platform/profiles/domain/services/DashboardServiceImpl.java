package pe.edu.upc.center.platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Dashboard;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateDashboardCommand;
import pe.edu.upc.center.platform.profiles.infrastructure.persistence.jpa.repositories.DashboardRepository;

import java.util.Optional;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final DashboardRepository dashboardRepository;

    public DashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public Optional<Dashboard> createDashboard(CreateDashboardCommand command) {
        Dashboard dashboard = new Dashboard(
                command.activeStudents(),
                command.scheduledSessions(),
                command.consultationHours(),
                command.averageRating(),
                command.totalRatings(),
                command.lastUpdate()
        );
        return Optional.of(dashboardRepository.save(dashboard));
    }

    @Override
    public Optional<Dashboard> getDashboard() {
        return dashboardRepository.findFirstByOrderByIdDesc();
    }
}