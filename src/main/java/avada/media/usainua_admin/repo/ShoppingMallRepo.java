package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.ShoppingMall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingMallRepo extends JpaRepository<ShoppingMall, Long> {
}
