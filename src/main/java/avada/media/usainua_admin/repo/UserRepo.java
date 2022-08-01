package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.PaymentTransaction;
import avada.media.usainua_admin.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    void deleteUserById(Long id);

}
