package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.user.PersonalData;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepo extends JpaRepository<PersonalData, Long> {

    @EntityGraph(attributePaths = {"user"})
    PersonalData findFullByUserId(Long id);

    @EntityGraph(attributePaths = {"user.roles"})
    PersonalData findFullWithRolesByUserId(Long id);

    PersonalData findBriefByUserId(Long id);

}
