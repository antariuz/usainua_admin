package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.PaymentTransaction;
import avada.media.usainua_admin.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransactionRepo extends JpaRepository<PaymentTransaction, Long> {
    void deletePaymentTransactionById(Long id);

    Page<PaymentTransaction> findByUserId(Long id, Pageable pageable);

}
