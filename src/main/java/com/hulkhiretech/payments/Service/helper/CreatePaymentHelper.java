package com.hulkhiretech.payments.Service.helper;

import com.hulkhiretech.payments.constants.Constant;
import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.pojo.CreatePaymentReq;
import com.hulkhiretech.payments.pojo.LineItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.concurrent.locks.Condition;

@Service
@Slf4j
public class CreatePaymentHelper {


    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${stripe.api.url}")
    private String stripeApiUrl;

    public HttpRequest prepareStripeCreateSessionRequest(CreatePaymentReq createPaymentReq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(stripeApiKey,"");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = prepareFormData(createPaymentReq);


        HttpRequest httpRequest = new HttpRequest();

        httpRequest.setMethod(HttpMethod.POST);
        httpRequest.setUrl(stripeApiUrl);
        httpRequest.setHeaders(httpHeaders);
        httpRequest.setRequestData(formData);
        return httpRequest;
    }

    public MultiValueMap<String, String> prepareFormData(CreatePaymentReq createPaymentReq) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        List<LineItem> lineItems = createPaymentReq.getLineItems();

        for (int i = 0; i < lineItems.size(); i++) {

            LineItem item = lineItems.get(i);
            String baseKey = Constant.LINE_ITEMS + Constant.OPEN_BRACKET + i + Constant.CLOSE_BRACKET;

            formData.add(baseKey + Constant.OPEN_BRACKET + Constant.PRICE_DATA + Constant.CLOSE_BRACKET +
                            Constant.OPEN_BRACKET + Constant.CURRENCY + Constant.CLOSE_BRACKET
                    , item.getCurrency());

            formData.add(baseKey + Constant.OPEN_BRACKET + Constant.PRICE_DATA + Constant.CLOSE_BRACKET +
                            Constant.OPEN_BRACKET + Constant.UNIT_AMOUNT + Constant.CLOSE_BRACKET
                    , String.valueOf(item.getUnitAmount()));

            formData.add(baseKey + Constant.OPEN_BRACKET + Constant.PRICE_DATA + Constant.CLOSE_BRACKET +
                            Constant.OPEN_BRACKET + Constant.PRODUCT_DATA + Constant.CLOSE_BRACKET +
                            Constant.OPEN_BRACKET + Constant.NAME + Constant.CLOSE_BRACKET
                    , item.getProductName());

            formData.add(baseKey + Constant.OPEN_BRACKET + Constant.QUANTITY + Constant.CLOSE_BRACKET,
                    String.valueOf(item.getQuantity()));
        }

        formData.add(Constant.CREATE_SESSION_MODE,
                Constant.CREATE_SESSION_MODE_PAYMENT);

        formData.add(Constant.SUCCESS_URL,
                createPaymentReq.getSuccessUrl());

        formData.add(Constant.CANCEL_URL,
                createPaymentReq.getCancelUrl());

        return formData;
    }

}
