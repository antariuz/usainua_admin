package avada.media.usainua_admin.service;


import avada.media.usainua_admin.model.Warehouse;

import java.util.List;

public interface WarehouseService {

    void saveWarehouse(Warehouse warehouse);

    void deleteWarehouse(Long id);

    Warehouse getWarehouseById(Long id);

    List<Warehouse> getAllWarehouses();

}
