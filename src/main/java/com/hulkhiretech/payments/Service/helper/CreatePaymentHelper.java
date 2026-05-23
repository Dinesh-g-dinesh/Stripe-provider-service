package com.hulkhiretech.payments.Service.helper;

import com.hulkhiretech.payments.constants.Constant;
import com.hulkhiretech.payments.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@Slf4j
public class CreatePaymentHelper {


    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${stripe.api.url}")
    private String stripeApiUrl;

    public HttpRequest prepareStripeCreateSessionRequest() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(stripeApiKey,"");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("line_items[0][price_data][currency]", "EUR");
        formData.add("line_items[0][price_data][unit_amount]", "100");
        formData.add("line_items[0][price_data][product_data][name]", "Phone xxx");
        formData.add("line_items[0][quantity]", "2");
        formData.add(Constant.CREATE_SESSION_MODE, "payment");
        formData.add(Constant.SUCCESS_URL, "https://example.com/success");


        HttpRequest httpRequest = new HttpRequest();

        httpRequest.setMethod(HttpMethod.POST);
        httpRequest.setUrl(stripeApiUrl);
        httpRequest.setHeaders(httpHeaders);
        httpRequest.setRequestData(formData);
        return httpRequest;
    }

}
