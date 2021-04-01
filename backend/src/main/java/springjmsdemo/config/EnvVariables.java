package springjmsdemo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class EnvVariables {

    @Value("${SENDER_ID:shopId}")
    private int senderId;

}
