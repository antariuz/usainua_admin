package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.Warehouse;
import avada.media.usainua_admin.repo.WarehouseRepo;
import avada.media.usainua_admin.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepo warehouseRepo;

    @Override
    public void saveWarehouse(Warehouse warehouse) {
        if (!warehouse.isMain()) {
            warehouseRepo.save(warehouse);
        } else {
            Warehouse mainWarehouse = warehouseRepo.getWarehouseByMainEquals(true);
            if (mainWarehouse != null) {
                mainWarehouse.setMain(false);
                warehouseRepo.save(mainWarehouse);
                warehouseRepo.save(warehouse);
            } else warehouseRepo.save(warehouse);
        }
    }

    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepo.deleteById(id);
        log.info("Warehouse with id {} was successfully deleted", id);
    }

    @Override
    public Warehouse getWarehouseById(Long id) {
        return warehouseRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Warehouse not found with id: " + id)
        );
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepo.findAll();
    }

}
