package avada.media.usainua_admin.controller;

import avada.media.usainua_admin.model.Warehouse;
import avada.media.usainua_admin.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/warehouses")
@RequiredArgsConstructor
@Slf4j
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping({"/", ""})
    public ModelAndView showWarehousesPage() {
        ModelAndView mav = new ModelAndView("warehouses/index");
        mav.addObject("warehouses", warehouseService.getAllWarehouses());
        mav.addObject("warehouse", new Warehouse());
        return mav;
    }

    @PostMapping("add")
    public String addWarehouse(@ModelAttribute("warehouse") Warehouse warehouse) {
        warehouseService.saveWarehouse(warehouse);
        if (warehouse.getId() == null) log.info("New Warehouse was successfully created");
        else log.info("New Warehouse with id {} was successfully updated", warehouse.getId());
        return "redirect:/warehouses";
    }

    @GetMapping("delete/{id}")
    public String deleteWarehouse(@PathVariable("id") Long id) {
        warehouseService.deleteWarehouse(id);
        return "redirect:/warehouses";
    }


}