package com.hulkhiretech.payments.Service.impl;

import com.hulkhiretech.payments.Service.helper.CreatePaymentHelper;
import com.hulkhiretech.payments.Service.interfaces.PaymentService;
import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.http.httpServiceEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final httpServiceEngine httpServiceEngine;
    private final CreatePaymentHelper createPaymentHelper;

    @Override
    public String createPayment() {

        HttpRequest httpRequest = createPaymentHelper.prepareStripeCreateSessionRequest();

        String httpResponse = httpServiceEngine.MakeHttpCall(httpRequest);

        log.info("Received response from HTTP call: {}", httpResponse);

        return httpResponse;
    }

}
