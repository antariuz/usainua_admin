package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, Long> {
    Staff getStaffByEmail(String email);
}
