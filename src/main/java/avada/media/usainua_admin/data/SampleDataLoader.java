package avada.media.usainua_admin.data;

import avada.media.usainua_admin.model.Staff;
import avada.media.usainua_admin.model.Warehouse;
import avada.media.usainua_admin.model.user.Role;
import avada.media.usainua_admin.repo.RoleRepo;
import avada.media.usainua_admin.repo.StaffRepo;
import avada.media.usainua_admin.repo.WarehouseRepo;
import avada.media.usainua_admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RequiredArgsConstructor
@Slf4j
@Component
public class SampleDataLoader implements CommandLineRunner {
    private final RoleRepo roleRepo;
    private final StaffRepo staffRepo;
    private final WarehouseRepo warehouseRepo;
    private final RoleService roleService;

    @Override
    @Transactional
    public void run(String... args) {
        log.info(
                System.getProperty("os.name") + "\n" +
                        System.getProperty("os.version") + "\n" +
                        System.getProperty("os.arch")
        );
        if (roleRepo.count() == 0) {
            log.info("Loading some data...");
            log.info("Roles...");
            roleRepo.saveAll(new ArrayList<>(Arrays.asList(
                    new Role("ROLE_ROOT"),
                    new Role("ROLE_ADMIN"),
                    new Role("ROLE_MANAGER"),
                    new Role("ROLE_USER")))
            );
        }

        if (staffRepo.getStaffByEmail("root@gmail.com") == null) {
            log.info("Creating root person...");
            staffRepo.save(
                    new Staff("root@gmail.com",
                            "$2a$10$NiRk/8GUWkv..sg6IyQCEuq0Ympeav5g8y5D0wZHlNwfGPJyu8.Hq",
                            new HashSet<>(Collections.singletonList(roleService.getRoleByName("ROLE_ROOT")))
                    ));
        }

        if (warehouseRepo.findAll().isEmpty()) {
            log.info("Warehouses...");
            warehouseRepo.saveAll(new ArrayList<>(Arrays.asList(
                    new Warehouse(
                            "645 W 1st Ave. ste DN01326",
                            "Roselle",
                            "New Jersey",
                            "07203",
                            "+1 908 241 21 90",
                            true
                    ),
                    new Warehouse(
                            "171 Edgemoor Road DN01326",
                            "Wilmington",
                            "Delaware",
                            "19809",
                            "+1 908 241 21 90",
                            false
                    )))
            );
        }
    }
}
