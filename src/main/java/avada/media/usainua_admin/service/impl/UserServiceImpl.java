package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.user.User;
import avada.media.usainua_admin.repo.UserRepo;
import avada.media.usainua_admin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final int USERS_PER_PAGE = 10;

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }

    @Override
    public Page<User> getPageUsers(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return userRepo.findAll(PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Override
    public Long countAllUsers() {
        return userRepo.count();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id " + id));
    }

}
