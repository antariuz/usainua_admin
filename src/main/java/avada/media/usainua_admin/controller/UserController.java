package avada.media.usainua_admin.controller;

import avada.media.usainua_admin.config.AppConst;
import avada.media.usainua_admin.model.PaymentTransaction;
import avada.media.usainua_admin.model.Product;
import avada.media.usainua_admin.model.order.Order;
import avada.media.usainua_admin.model.user.PersonalData;
import avada.media.usainua_admin.model.user.User;
import avada.media.usainua_admin.service.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PersonalDataService personalDataService;
    private final OrderService orderService;
    private final PaymentTransactionService paymentTransactionService;
    private final ProductService productService;

    @GetMapping({"/", ""})
    public String getFirstPageUsers() {
        return "redirect:/users/page/1";
    }

    @Transactional
    @GetMapping("page/{pageNumber}")
    public ModelAndView getPageUsers(@PathVariable("pageNumber") int pageNumber) {
        ModelAndView mav = new ModelAndView("/users/index");
        Page<User> page = userService.getPageUsers(
                pageNumber - 1, AppConst.DEFAULT_PAGE_SIZE, AppConst.DEFAULT_SORT_BY, AppConst.DEFAULT_SORT_DIRECTION);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<User> users = page.getContent();

        List<PersonalData> briefPersonalDataList = users.stream()
                .map(user -> personalDataService.getBriefPersonalDataByUserId(user.getId()))
                .collect(Collectors.toList());

        int begin = Math.max(1, pageNumber - 3);
        int end = Math.min(begin + 6, totalPages);
        mav.addObject("currentPage", pageNumber);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("exchangeRate", AppConst.EXCHANGE_RATE);
        mav.addObject("begin", begin);
        mav.addObject("end", end);
        mav.addObject("users", users);
        mav.addObject("personalDataList", briefPersonalDataList);
        return mav;
    }

    @GetMapping("{id}/orders")
    public String getUserOrdersPage(@PathVariable("id") Long id) {
        return "redirect:/users/" + id + "/orders/page/1";
    }

    @GetMapping("{id}/orders/page/{pageNumber}")
    public ModelAndView getPageOrdersUser(@PathVariable("id") Long id,
                                          @PathVariable("pageNumber") int pageNumber) {
        ModelAndView mav = new ModelAndView("/users/orders");
        Page<Order> page = orderService.getPageOrdersByUserId
                (id, pageNumber - 1, AppConst.DEFAULT_PAGE_SIZE, AppConst.DEFAULT_SORT_BY, AppConst.DEFAULT_SORT_DIRECTION);
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
        mav.addObject("user", userService.getUserById(id));
        mav.addObject("personalData", personalDataService.getBriefPersonalDataByUserId(id));
        return mav;
    }

    @GetMapping("{id}/transactions")
    public String getUserPaymentsPage(@PathVariable("id") Long id) {
        return "redirect:/users/" + id + "/transactions/page/1";
    }

    @GetMapping("{id}/transactions/page/{pageNumber}")
    public ModelAndView getPagePaymentsUser(@PathVariable("id") Long id,
                                            @PathVariable("pageNumber") int pageNumber) {
        ModelAndView mav = new ModelAndView("/users/transactions");
        Page<PaymentTransaction> page =
                paymentTransactionService.getPagePaymentTransactionsByUserId
                        (id, pageNumber - 1, AppConst.DEFAULT_PAGE_SIZE, AppConst.DEFAULT_SORT_BY, AppConst.DEFAULT_SORT_DIRECTION);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<PaymentTransaction> transactions = page.getContent();
        int begin = Math.max(1, pageNumber - 3);
        int end = Math.min(begin + 6, totalPages);
        mav.addObject("currentPage", pageNumber);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("exchangeRate", AppConst.EXCHANGE_RATE);
        mav.addObject("begin", begin);
        mav.addObject("end", end);
        mav.addObject("transactions", transactions);
        mav.addObject("user", userService.getUserById(id));
        mav.addObject("personalData", personalDataService.getBriefPersonalDataByUserId(id));
        return mav;
    }

    @GetMapping("{id}/wishlist")
    public String getUserWishlistPage(@PathVariable("id") Long id) {
        return "redirect:/users/" + id + "/wishlist/page/1";
    }

    @GetMapping("{id}/wishlist/page/{pageNumber}")
    public ModelAndView getPageWishlistUser(@PathVariable("id") Long id,
                                            @PathVariable("pageNumber") int pageNumber) {
        ModelAndView mav = new ModelAndView("/users/wishlist");
        Page<Product> page = productService.getPageProductsByUserId(
                id, pageNumber - 1, AppConst.DEFAULT_PAGE_SIZE, AppConst.DEFAULT_SORT_BY, AppConst.DEFAULT_SORT_DIRECTION);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> wishlist = page.getContent();
        int begin = Math.max(1, pageNumber - 3);
        int end = Math.min(begin + 6, totalPages);
        mav.addObject("currentPage", pageNumber);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("exchangeRate", AppConst.EXCHANGE_RATE);
        mav.addObject("begin", begin);
        mav.addObject("end", end);
        mav.addObject("wishlist", wishlist);
        mav.addObject("user", userService.getUserById(id));
        mav.addObject("personalData", personalDataService.getBriefPersonalDataByUserId(id));
        return mav;
    }

    @GetMapping("{id}/delete")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}