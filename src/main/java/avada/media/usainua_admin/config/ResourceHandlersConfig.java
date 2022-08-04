package avada.media.usainua_admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.file.Paths;

@Configuration
public class ResourceHandlersConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = System.getProperty("os.name").contains("Linux") ?
                Paths.get("/var/lib/tomcat9/uploaded/").toFile().getAbsolutePath() : Paths.get("uploaded").toFile().getAbsolutePath();
        //        Upload folder
        registry.addResourceHandler("/uploaded/**").addResourceLocations("file://" + uploadPath + "/");
        //        Static folder
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}
