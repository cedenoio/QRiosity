import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author yoandy
 * @since 10/5/13
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.qriosity.mvc.controller"})
public class AppConfig {

}
