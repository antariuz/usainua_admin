package avada.media.usainua_admin.service;

import avada.media.usainua_admin.model.user.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleByName(String name);

}
