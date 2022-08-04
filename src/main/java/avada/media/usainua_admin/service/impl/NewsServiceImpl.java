package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.News;
import avada.media.usainua_admin.repo.NewsRepo;
import avada.media.usainua_admin.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;

    @Override
    public void saveNews(News news) {
        newsRepo.save(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsRepo.deleteById(id);
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("News not found with id: " + id));
    }

    @Override
    public List<News> getAllNews() {
        return newsRepo.findAll();
    }

    @Override
    public Long countAllNews() {
        return newsRepo.count();
    }

}
