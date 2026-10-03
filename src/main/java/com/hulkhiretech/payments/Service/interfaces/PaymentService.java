package com.hulkhiretech.payments.Service.interfaces;

import com.hulkhiretech.payments.Stripe.CheckoutSessionResponse;
import com.hulkhiretech.payments.pojo.CreatePaymentReq;
import com.hulkhiretech.payments.pojo.PaymentResponse;

public interface PaymentService {

    public PaymentResponse createPayment(CreatePaymentReq createPaymentReq);

}
