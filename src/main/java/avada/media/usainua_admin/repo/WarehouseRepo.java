package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
    Warehouse getWarehouseByMainEquals(boolean main);

}
