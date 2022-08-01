package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.user.PersonalData;
import avada.media.usainua_admin.model.user.User;
import avada.media.usainua_admin.repo.PersonalDataRepo;
import avada.media.usainua_admin.service.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {

    private final PersonalDataRepo personalDataRepo;

    @Override
    public PersonalData getBriefPersonalDataByUserId(Long id) {
        return personalDataRepo.findBriefByUserId(id);
    }

    @Override
    public PersonalData getFullPersonalDataByUserId(Long id) {
        return personalDataRepo.findFullByUserId(id);
    }

    @Override
    public PersonalData getFullWithRolesPersonalDataByUserId(Long id) {
        return personalDataRepo.findFullWithRolesByUserId(id);
    }

}
