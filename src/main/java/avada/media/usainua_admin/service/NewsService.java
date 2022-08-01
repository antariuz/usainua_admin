package avada.media.usainua_admin.service;


import avada.media.usainua_admin.model.News;

import java.util.List;

public interface NewsService {

    void saveNews(News news);

    void deleteNews(Long id);

    News getNewsById(Long id);

    List<News> getAllNews();

    Long countAllNews();

}
