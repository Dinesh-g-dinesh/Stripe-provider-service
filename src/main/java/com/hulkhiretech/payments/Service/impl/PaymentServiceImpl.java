package com.hulkhiretech.payments.Service.impl;

import com.hulkhiretech.payments.Service.helper.CreatePaymentHelper;
import com.hulkhiretech.payments.Service.interfaces.PaymentService;
import com.hulkhiretech.payments.Stripe.CheckoutSessionResponse;
import com.hulkhiretech.payments.Util.JsonUtil;
import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.http.httpServiceEngine;
import com.hulkhiretech.payments.pojo.CreatePaymentReq;
import com.hulkhiretech.payments.pojo.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final httpServiceEngine httpServiceEngine;
    private final CreatePaymentHelper createPaymentHelper;
    private final JsonUtil jsonUtil;

    @Override
    public PaymentResponse createPayment(CreatePaymentReq createPaymentReq) {

        HttpRequest httpRequest = createPaymentHelper
                .prepareStripeCreateSessionRequest(createPaymentReq);

        ResponseEntity<String> httpResponse = httpServiceEngine.MakeHttpCall(httpRequest);

        CheckoutSessionResponse checkoutSessionResponse =
                jsonUtil.convertJsonToObject(
                        httpResponse.getBody(),
                        CheckoutSessionResponse.class);

        PaymentResponse paymentResponse = mapCheckoutSessionResponseToPaymentResponse(checkoutSessionResponse);
        log.info("Mapped PaymentResponse: {}", paymentResponse);
        return paymentResponse;
    }

    /**
     * write a map method to take checkoutsessionResponse
     * and convert it to paymentResponse which is
     * our internal response object. this way we are not
     */
    public PaymentResponse mapCheckoutSessionResponseToPaymentResponse(CheckoutSessionResponse checkoutSessionResponse) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setSessionId(checkoutSessionResponse.getId());
        paymentResponse.setHostUrl(checkoutSessionResponse.getUrl());
        return paymentResponse;
    }


}
