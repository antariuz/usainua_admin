package avada.media.usainua_admin.controller;

import avada.media.usainua_admin.config.AppConst;
import avada.media.usainua_admin.model.Product;
import avada.media.usainua_admin.model.order.Order;
import avada.media.usainua_admin.model.order.SubOrder;
import avada.media.usainua_admin.repo.SubOrderRepo;
import avada.media.usainua_admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;
    private final SubOrderRepo subOrderRepo;

    @GetMapping({"/", ""})
    public String getFirstPageOrders() {
        return "redirect:/orders/page/1";
    }

    @GetMapping("page/{pageNumber}")
    public ModelAndView getPageOrders(@PathVariable("pageNumber") int pageNumber) {
        ModelAndView mav = new ModelAndView("/orders/index");
        Page<Order> page = orderService.getPageOrders
                (pageNumber - 1, AppConst.DEFAULT_PAGE_SIZE, AppConst.DEFAULT_SORT_BY, AppConst.DEFAULT_SORT_DIRECTION);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Order> orders = page.getContent();
        int begin = Math.max(1, pageNumber - 3);
        int end = Math.min(begin + 6, totalPages);
        mav.addObject("currentPage", pageNumber);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("exchangeRate", AppConst.EXCHANGE_RATE);
        mav.addObject("begin", begin);
        mav.addObject("end", end);
        mav.addObject("orders", orders);
        mav.addObject("newOrders", orderService.countOrdersWithNewStatus());
        return mav;
    }

    @GetMapping("new/page/{pageNumber}")
    public ModelAndView getPageNewOrders(@PathVariable("pageNumber") int pageNumber) {
        ModelAndView mav = new ModelAndView("/orders/new");
        Page<Order> page = orderService.getPageOrdersWithNewStatus
                (pageNumber - 1, AppConst.DEFAULT_PAGE_SIZE, AppConst.DEFAULT_SORT_BY, AppConst.DEFAULT_SORT_DIRECTION);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Order> orders = page.getContent();
        int begin = Math.max(1, pageNumber - 3);
        int end = Math.min(begin + 6, totalPages);
        mav.addObject("currentPage", pageNumber);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("exchangeRate", AppConst.EXCHANGE_RATE);
        mav.addObject("begin", begin);
        mav.addObject("end", end);
        mav.addObject("orders", orders);
        return mav;
    }

    @GetMapping("{id}/view")
    public ModelAndView viewOrder(@PathVariable("id") Long id,
                                  @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        ModelAndView mav = new ModelAndView("/orders/view");
        mav.addObject("exchangeRate", AppConst.EXCHANGE_RATE);
        mav.addObject("order", orderService.getOrderById(id));
        mav.addObject("suborder", new SubOrder());
        if (referrer != null) {
            mav.addObject("previousUrl", referrer);
        }
        return mav;
    }

    @GetMapping("suborder/{id}/view")
    public @ResponseBody SubOrder getSuborder(@PathVariable("id") Long id) {
        return subOrderRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Suborder not found with id: " + id));
    }

    @PostMapping("{id}/suborder/update")
    public String updateSuborder(@ModelAttribute SubOrder subOrderRequested,
                                 @PathVariable("id") Long id,
                                 @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        SubOrder subOrder = subOrderRepo.findById(subOrderRequested.getId()).orElseThrow(() -> new EntityNotFoundException("Suborder not found with id: " + id));
        subOrder.setDescription(subOrderRequested.getDescription());
        subOrder.setEstimateWeight(subOrderRequested.getEstimateWeight());
        subOrder.setPrice(subOrderRequested.getPrice());
        subOrder.setQty(subOrderRequested.getQty());
        subOrder.setUrl(subOrderRequested.getUrl());
        subOrderRepo.save(subOrder);
        orderService.calculateOrder(id);
        return "redirect:" + referrer;
    }

    @GetMapping("{id}/delete")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

}