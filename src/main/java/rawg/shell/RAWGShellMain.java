package rawg.shell;

import org.jline.utils.AttributedString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.shell.jline.PromptProvider;

/**
 * Command Shell for interactions with RAWG API
 * 
 * Requires OS Environment variable RAWG_API_KEY with an RAWG API key.  If missing 
 * an exception will be thrown and the process will terminate. 
 */
@SpringBootApplication
@ComponentScan(basePackages = {"rawg.shell"})
public class RAWGShellMain {
    public static void main(String[] args) {
        SpringApplication.run(RAWGShellMain.class, args);
    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("rawg:>");
    }

    /**
     * Used by VersionCommands to bootstrap the load of the git.properties file
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propsConfig 
          = new PropertySourcesPlaceholderConfigurer();
        propsConfig.setLocation(new ClassPathResource("git.properties"));
        propsConfig.setIgnoreResourceNotFound(true);
        propsConfig.setIgnoreUnresolvablePlaceholders(true);
        return propsConfig;
    }    
}