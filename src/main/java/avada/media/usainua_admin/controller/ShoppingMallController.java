package avada.media.usainua_admin.controller;

import avada.media.usainua_admin.model.ShoppingMall;
import avada.media.usainua_admin.model.common.FileUploadUtil;
import avada.media.usainua_admin.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/shopping-malls")
@RequiredArgsConstructor
@Slf4j
public class ShoppingMallController {

    private final ShoppingMallService shoppingMallService;

    @GetMapping({"/", ""})
    public ModelAndView showShoppingMallsPage() {
        ModelAndView mav = new ModelAndView("shopping-malls/index");
        mav.addObject("shoppingMalls", shoppingMallService.getAllShoppingMalls());
        mav.addObject("newShoppingMall", new ShoppingMall());
        return mav;
    }

    @PostMapping("add")
    public String addShoppingMall(@ModelAttribute("newShoppingMall") ShoppingMall newShoppingMall,
                                  @RequestParam("addImg") MultipartFile img) throws IOException {
        String imageName = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
        String uploadDir = "";
        if (!imageName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, imageName, img);
            newShoppingMall.setImage(imageName);
        } else throw new FileNotFoundException("Image file has not been uploaded");
        shoppingMallService.createShoppingMall(newShoppingMall);
        log.info("New Shopping Mall was successfully created");
        return "redirect:/shopping-malls";
    }

    @GetMapping("edit/{id}")
    public @ResponseBody ShoppingMall getShoppingMall(@PathVariable("id") Long id) {
        return shoppingMallService.getShoppingMallById(id);
    }

    @GetMapping("delete/{id}")
    public String deleteShoppingMall(@PathVariable("id") Long id) {
        shoppingMallService.deleteShoppingMall(id);
        return "redirect:/shopping-malls";
    }


}