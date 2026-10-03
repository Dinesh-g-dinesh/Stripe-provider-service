package com.hulkhiretech.payments.http;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class httpServiceEngine {

    private final RestClient restClient;
    public ResponseEntity<String> MakeHttpCall(HttpRequest httpRequest){
        log.info("Making HTTP call to external payment provider...");

         ResponseEntity<String> httpResponse = restClient.method(httpRequest.getMethod())
                .uri(httpRequest.getUrl())
                .headers(headers -> headers.addAll(httpRequest.getHeaders()))
                .body(httpRequest.getRequestData())
                .retrieve()
                .toEntity(String.class);

         log.info("HTTP call completed with status code: {}, Response Body: {}",
                 httpResponse.getStatusCode(), httpResponse);

        return httpResponse;
    }

    @PostConstruct
    public void init(){
        log.info("httpServiceEngine initialized with RestClient: {}", restClient);
    }
}
