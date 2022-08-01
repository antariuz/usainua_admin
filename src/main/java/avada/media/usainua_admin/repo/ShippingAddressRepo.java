package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.delivery.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepo extends JpaRepository<ShippingAddress, Long> {
}
