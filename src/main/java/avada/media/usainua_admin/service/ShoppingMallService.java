package avada.media.usainua_admin.service;


import avada.media.usainua_admin.model.ShoppingMall;

import java.util.List;

public interface ShoppingMallService {

    void createShoppingMall(ShoppingMall shoppingMall);

    void updateShoppingMall(ShoppingMall shoppingMall);

    void deleteShoppingMall(Long id);

    ShoppingMall getShoppingMallById(Long id);

    List<ShoppingMall> getAllShoppingMalls();

}
