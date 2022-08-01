package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.ShoppingMall;
import avada.media.usainua_admin.repo.ShoppingMallRepo;
import avada.media.usainua_admin.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingMallServiceImpl implements ShoppingMallService {

    private final ShoppingMallRepo shoppingMallRepo;

    @Override
    public void createShoppingMall(ShoppingMall shoppingMall) {
        shoppingMallRepo.save(shoppingMall);
    }

    @Override
    public void updateShoppingMall(ShoppingMall shoppingMall) {
        shoppingMallRepo.save(shoppingMall);
    }

    @Override
    public void deleteShoppingMall(Long id) {
        shoppingMallRepo.deleteById(id);
        log.info("Shopping Mall with id {} was successfully deleted", id);
    }

    @Override
    public ShoppingMall getShoppingMallById(Long id) {
        return shoppingMallRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Shopping Mall not found with id: " + id)
        );
    }

    @Override
    public List<ShoppingMall> getAllShoppingMalls() {
        return shoppingMallRepo.findAll();
    }
}
