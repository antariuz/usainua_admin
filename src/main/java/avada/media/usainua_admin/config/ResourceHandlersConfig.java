package avada.media.usainua_admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ResourceHandlersConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        exposeDirectory("uploaded", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("/var/lib/tomcat9/webapps/ROOT/uploaded");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file://" + uploadPath + "/");
    }

}
