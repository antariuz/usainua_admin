package avada.media.usainua_admin.service;

import avada.media.usainua_admin.model.user.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    void deleteUser(Long id);

    User getUserById(Long id);

    Page<User> getPageUsers(int pageNumber, int pageSize, String sortBy, String sortDirection);

    Long countAllUsers();

}
