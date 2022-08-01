package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.user.Role;
import avada.media.usainua_admin.repo.RoleRepo;
import avada.media.usainua_admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;


    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepo.findRoleByName(name).orElseThrow(
                () -> new EntityNotFoundException("Role not found with name: " + name)
        );
    }
}
