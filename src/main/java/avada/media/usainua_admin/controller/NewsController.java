package avada.media.usainua_admin.controller;

import avada.media.usainua_admin.model.News;
import avada.media.usainua_admin.model.common.FileUploadUtil;
import avada.media.usainua_admin.service.NewsService;
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
@RequestMapping("/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    private final NewsService newsService;

    @GetMapping({"/", ""})
    public ModelAndView showNewsPage() {
        ModelAndView mav = new ModelAndView("news/index");
        mav.addObject("newsList", newsService.getAllNews());
        mav.addObject("newNews", new News());
        return mav;
    }

    @PostMapping("add")
    public String addNews(@ModelAttribute("news") News news,
                          @RequestParam("addImg") MultipartFile img) throws IOException {
        String imageName = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
        String uploadDir = "";
        if (!imageName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, imageName, img);
            news.setImage(imageName);
        } else throw new FileNotFoundException("Image file has not been uploaded");
        newsService.saveNews(news);
        log.info("New News was successfully created");
        return "redirect:/news";
    }

    @GetMapping("edit/{id}")
    public @ResponseBody News getNews(@PathVariable("id") Long id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("delete/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return "redirect:/news";
    }

}