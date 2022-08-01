package avada.media.usainua_admin.service;

import avada.media.usainua_admin.model.user.PersonalData;

public interface PersonalDataService {

    PersonalData getBriefPersonalDataByUserId(Long id);

    PersonalData getFullPersonalDataByUserId(Long id);

    PersonalData getFullWithRolesPersonalDataByUserId(Long id);

}
