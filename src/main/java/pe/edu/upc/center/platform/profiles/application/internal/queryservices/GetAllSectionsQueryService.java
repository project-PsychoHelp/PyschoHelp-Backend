package pe.edu.upc.center.platform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Section;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetAllSectionsQuery;
import pe.edu.upc.center.platform.profiles.domain.services.SectionService;

import java.util.List;

@Service
public class GetAllSectionsQueryService {
    private final SectionService sectionService;


    public GetAllSectionsQueryService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public List<Section> handle(GetAllSectionsQuery query) {
        return sectionService.getAllSections();
    }
}