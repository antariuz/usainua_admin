package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    void deleteOrderById(Long id);

    Page<Order> findByUserId(Long id, Pageable pageable);

    Page<Order> findByStatusEquals(Order.Status status, Pageable pageable);

    Long countByStatusEquals(Order.Status status);

}
