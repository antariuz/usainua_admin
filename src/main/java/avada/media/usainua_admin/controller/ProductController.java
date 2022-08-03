package avada.media.usainua_admin.controller;

import avada.media.usainua_admin.config.AppConst;
import avada.media.usainua_admin.model.Product;
import avada.media.usainua_admin.model.ShoppingMall;
import avada.media.usainua_admin.model.Warehouse;
import avada.media.usainua_admin.model.user.User;
import avada.media.usainua_admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping({"/", ""})
    public String getMainPage() {
        return "redirect:/products/page/1";
    }

    @GetMapping("page/{pageNumber}")
    public ModelAndView getOnePage(@PathVariable("pageNumber") int pageNumber) {
        ModelAndView mav = new ModelAndView("/products/index");
        Page<Product> page = productService.getPageProducts(
                pageNumber - 1, AppConst.DEFAULT_PAGE_SIZE, AppConst.DEFAULT_SORT_BY, AppConst.DEFAULT_SORT_DIRECTION);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> products = page.getContent();
        int begin = Math.max(1, pageNumber - 3);
        int end = Math.min(begin + 6, totalPages);
        mav.addObject("currentPage", pageNumber);
        mav.addObject("totalPages", totalPages);
        mav.addObject("totalItems", totalItems);
        mav.addObject("exchangeRate", AppConst.EXCHANGE_RATE);
        mav.addObject("begin", begin);
        mav.addObject("end", end);
        mav.addObject("products", products);
        mav.addObject("product", new Product());
        return mav;
    }

    @PostMapping("add")
    public String addProduct(@ModelAttribute("product") Product productRequest) {
        if (productRequest.getId() != null) {
            Product product = productService.getProductById(productRequest.getId());
            product.setName(productRequest.getName());
            product.setUrl(productRequest.getUrl());
            product.setPrice(productRequest.getPrice());
            product.setDescription(productRequest.getDescription());
            productService.saveProduct(product);
        } else productService.saveProduct(productRequest);
        return "redirect:/products";
    }

    @GetMapping("{id}/view")
    public ModelAndView viewProduct(@PathVariable("id") Long id) {
        return new ModelAndView("/products/view", "product", productService.getProductById(id));
    }

    @GetMapping("{id}/edit")
    public @ResponseBody Product getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("{id}/delete")
    public String deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

}