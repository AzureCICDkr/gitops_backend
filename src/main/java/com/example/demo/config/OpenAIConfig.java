package com.example.demo.config;


import com.example.demo.dto.OpenAiChatProperties;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openai")
public class OpenAIConfig {


    @Bean
    public OpenAiApi openAiApi(
            @Value("${spring.ai.openai.api-key}") String apiKey
    ) {
        return new OpenAiApi(apiKey);
    }

    @Bean
    public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi, OpenAiChatProperties props) {
       OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model(props.getModel())
                .temperature(props.getTemperature())
                .maxTokens(props.getMaxTokens())
                .build();
       return new OpenAiChatModel(openAiApi, openAiChatOptions);
    }

    @Bean
    ChatClient chatClient(OpenAiChatModel model) {
        return ChatClient.builder(model).build();
    }

    private String apiKey;
    private String model;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
