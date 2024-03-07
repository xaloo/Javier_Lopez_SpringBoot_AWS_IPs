package javier.lopez.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CustomWebClient {

    @Value("${webclient.buffer.size.bytes}")
    private int webClientBufferSizeBytes;

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(webClientBufferSizeBytes * 1024))
                .build();
    }
}

