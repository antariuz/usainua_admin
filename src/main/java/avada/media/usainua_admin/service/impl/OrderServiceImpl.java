package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.config.AppConst;
import avada.media.usainua_admin.model.order.Order;
import avada.media.usainua_admin.model.order.SubOrder;
import avada.media.usainua_admin.repo.OrderRepo;
import avada.media.usainua_admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public void saveOrder(Order order) {
        List<SubOrder> orders = order.getSubOrders();
        Double sumSubOrdersPrice = orders.stream().mapToDouble(subOrder -> subOrder.getPrice() * subOrder.getQty()).sum();
        Double sumSubOrdersEstimateWeight = orders.stream().mapToDouble(SubOrder::getEstimateWeight).sum();
        Double commissionPrice = sumSubOrdersPrice * AppConst.COMMISSION_RATE;
        Double insurancePrice = sumSubOrdersPrice * AppConst.INSURANCE_RATE;
        Double clearancePrice = orders.size() * AppConst.CLEARANCE_PER_PRODUCT;
        Double deliveryPrice = order.getDeliveryType().equals(Order.DeliveryType.SEA) ?
                sumSubOrdersEstimateWeight * AppConst.SEA_DELIVERY_RATE : sumSubOrdersEstimateWeight * AppConst.AIR_DELIVERY_RATE;
        Double totalPrice = sumSubOrdersPrice + commissionPrice + insurancePrice + clearancePrice + deliveryPrice;
        order.setCommissionPrice(commissionPrice);
        order.setInsurancePrice(insurancePrice);
        order.setClearancePrice(clearancePrice);
        order.setDeliveryPrice(deliveryPrice);
        order.setTotalPrice(totalPrice);
        order.setTotalWeight(orders.stream().mapToDouble(SubOrder::getEstimateWeight).sum());
        orderRepo.save(order);
    }

    @Override
    public void calculateOrder(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        List<SubOrder> subOrders = order.getSubOrders();
        if (subOrders.stream().noneMatch(subOrder -> subOrder.getPrice() <= 0) &&
                subOrders.stream().noneMatch(subOrder -> subOrder.getQty() <= 0) &&
                subOrders.stream().noneMatch(subOrder -> subOrder.getEstimateWeight() <= 0)) {
            Double sumSubOrdersPrice = subOrders.stream().mapToDouble(subOrder -> subOrder.getPrice() * subOrder.getQty()).sum();
            Double sumSubOrdersEstimateWeight = subOrders.stream().mapToDouble(SubOrder::getEstimateWeight).sum();
            Double commissionPrice = sumSubOrdersPrice * AppConst.COMMISSION_RATE;
            Double insurancePrice = sumSubOrdersPrice * AppConst.INSURANCE_RATE;
            Double clearancePrice = subOrders.size() * AppConst.CLEARANCE_PER_PRODUCT;
            Double deliveryPrice = order.getDeliveryType().equals(Order.DeliveryType.SEA) ?
                    sumSubOrdersEstimateWeight * AppConst.SEA_DELIVERY_RATE : sumSubOrdersEstimateWeight * AppConst.AIR_DELIVERY_RATE;
            Double totalPrice = sumSubOrdersPrice + commissionPrice + insurancePrice + clearancePrice + deliveryPrice;
            order.setCommissionPrice(commissionPrice);
            order.setInsurancePrice(insurancePrice);
            order.setClearancePrice(clearancePrice);
            order.setDeliveryPrice(deliveryPrice);
            order.setTotalPrice(totalPrice);
            order.setTotalWeight(subOrders.stream().mapToDouble(SubOrder::getEstimateWeight).sum());
            order.setStatus(Order.Status.WAITING_PAYMENT);
            orderRepo.save(order);
        }
    }

    @Override
    public void updateOrder(Order order) {
        orderRepo.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteOrderById(id);
    }

    @Override
    public Page<Order> getPageOrders(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return orderRepo.findAll(PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
    }

    @Override
    public Long countAllOrders() {
        return orderRepo.count();
    }

    @Transactional
    @Override
    public Page<Order> getPageOrdersByUserId(Long id, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return orderRepo.findByUserId(id, PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Transactional
    @Override
    public Page<Order> getPageOrdersWithNewStatus(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return orderRepo.findByStatusEquals(Order.Status.CALCULATING_ORDER, PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Override
    public Long countOrdersWithNewStatus() {
        return orderRepo.countByStatusEquals(Order.Status.CALCULATING_ORDER);
    }

}
