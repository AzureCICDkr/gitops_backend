package com.example.demo.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "spring.ai.openai.chat")
public class OpenAiChatProperties {

    private String model;
    private Double temperature;
    private Integer maxTokens;


    @Override
    public String toString() {
        return "OpenAiChatProperties{" +
                "model='" + model + '\'' +
                ", temperature=" + temperature +
                ", maxTokens=" + maxTokens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OpenAiChatProperties that = (OpenAiChatProperties) o;
        return Objects.equals(model, that.model) && Objects.equals(temperature, that.temperature) && Objects.equals(maxTokens, that.maxTokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, temperature, maxTokens);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    // getters / setters
}