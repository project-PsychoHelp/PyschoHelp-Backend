package pe.edu.upc.center.platform.student.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.student.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.student.domain.model.valueobjects.ProfileId;
import pe.edu.upc.center.platform.student.interfaces.rest.resources.StudentResource;
import pe.edu.upc.center.platform.profiles.interfaces.acl.ProfilesContextFacade;

import java.util.Optional;

@Service
public class ExternalProfileService {

  private final ProfilesContextFacade profilesContextFacade;

  public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
    this.profilesContextFacade = profilesContextFacade;
  }

  public Optional<ProfileId> fetchProfileIdByFullName(String fullName) {
    var profileId = this.profilesContextFacade.fetchProfileIdByFullName(fullName);
    if (profileId.equals(0L))
      return Optional.empty();
    return Optional.of(new ProfileId(profileId));
  }

  public boolean existsProfileByFullNameAndIdIsNot(String fullName, long id) {
    return this.profilesContextFacade.existsProfileByFullNameAndIdIsNot(fullName, id);
  }

  public Optional<ProfileId> createProfile(String fullName, int age, String street) {
    var profileId = this.profilesContextFacade.createProfile(fullName, age, street);
    if (profileId.equals(0L))
      return Optional.empty();
    return Optional.of(new ProfileId(profileId));
  }

  public Optional<ProfileId> updateProfile(Long profileId, String fullName, int age, String street) {
    var profileIdUpdated = this.profilesContextFacade.updateProfile(profileId, fullName, age, street);
    if (profileIdUpdated.equals(0L))
      return Optional.empty();
    return Optional.of(new ProfileId(profileIdUpdated));
  }

  public void deleteProfile(Long profileId) {
    this.profilesContextFacade.deleteProfile(profileId);
  }

  // Evaluate if these method is necessary here or implement it in the transform layer
  public Optional<StudentResource> fetchStudentResourceFromProfileId(Student student) {
    var profileResource = this.profilesContextFacade.fetchProfileById(student.getProfileId());
    if (profileResource.isEmpty())
      return Optional.empty();

    var studentResource = new StudentResource(student.getStudentCode().studentCode(), profileResource.get().fullName(),
        profileResource.get().age(), profileResource.get().street(), student.getProgramId(), student.getStartPeriod());
    return Optional.of(studentResource);
  }
}
