package avada.media.usainua_admin.service;

import avada.media.usainua_admin.model.PaymentTransaction;
import avada.media.usainua_admin.model.order.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentTransactionService {

    void savePaymentTransaction(PaymentTransaction paymentTransaction);

    void deletePaymentTransaction(Long id);

    PaymentTransaction getPaymentTransactionById(Long id);

    Page<PaymentTransaction> getPagePaymentTransactions(int pageNumber, int pageSize, String sortBy, String sortDirection);

    Page<PaymentTransaction> getPagePaymentTransactionsByUserId(Long id, int pageNumber, int pageSize, String sortBy, String sortDirection);

    Long countAllPaymentTransactions();

}
