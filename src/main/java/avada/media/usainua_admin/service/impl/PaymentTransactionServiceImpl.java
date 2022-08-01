package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.PaymentTransaction;
import avada.media.usainua_admin.repo.PaymentTransactionRepo;
import avada.media.usainua_admin.service.PaymentTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    private final PaymentTransactionRepo paymentTransactionRepo;

    @Override
    public void savePaymentTransaction(PaymentTransaction paymentTransaction) {
        paymentTransactionRepo.save(paymentTransaction);
    }

    @Override
    public void deletePaymentTransaction(Long id) {
        paymentTransactionRepo.deletePaymentTransactionById(id);
    }

    @Override
    public Page<PaymentTransaction> getPagePaymentTransactions(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return paymentTransactionRepo.findAll(PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Override
    public Page<PaymentTransaction> getPagePaymentTransactionsByUserId(Long id, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return paymentTransactionRepo.findByUserId(id, PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Override
    public Long countAllPaymentTransactions() {
        return paymentTransactionRepo.count();
    }

    @Override
    public PaymentTransaction getPaymentTransactionById(Long id) {
        return paymentTransactionRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("PaymentTransaction not found with id " + id));
    }

}
