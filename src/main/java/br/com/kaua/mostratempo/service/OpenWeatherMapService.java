package br.com.kaua.mostratempo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenWeatherMapService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    @Autowired
    public OpenWeatherMapService(RestTemplate restTemplate,
                                 @Value("${openweathermap.api.url}") String baseUrl,
                                 @Value("${openweahtermap.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public String buscarTempoPelaCidade(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
