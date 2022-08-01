package avada.media.usainua_admin.service;

import avada.media.usainua_admin.model.order.Order;
import org.springframework.data.domain.Page;

public interface OrderService {

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Long id);

    Page<Order> getPageOrders(int pageNumber, int pageSize, String sortBy, String sortDirection);

    Order getOrderById(Long id);

    Long countAllOrders();

    Page<Order> getPageOrdersByUserId(Long id, int pageNumber, int pageSize, String sortBy, String sortDirection);

    Page<Order> getPageOrdersWithNewStatus(int pageNumber, int pageSize, String sortBy, String sortDirection);

    Long countOrdersWithNewStatus();

    void calculateOrder(Long orderId);

}
