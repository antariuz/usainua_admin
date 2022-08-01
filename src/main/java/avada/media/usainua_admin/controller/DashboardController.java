package avada.media.usainua_admin.controller;

import avada.media.usainua_admin.service.NewsService;
import avada.media.usainua_admin.service.OrderService;
import avada.media.usainua_admin.service.ProductService;
import avada.media.usainua_admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final UserService userService;
    private final OrderService orderService;
    private final NewsService newsService;
    private final ProductService productService;

    @GetMapping({"/", ""})
    public ModelAndView showDashboardPage() {
        ModelAndView mav = new ModelAndView("dashboard");
        mav.addObject("totalUsers", userService.countAllUsers());
        mav.addObject("totalOrders", orderService.countAllOrders());
        mav.addObject("totalNews", newsService.countAllNews());
        mav.addObject("totalProducts", productService.countAllProducts());
        return mav;
    }

}