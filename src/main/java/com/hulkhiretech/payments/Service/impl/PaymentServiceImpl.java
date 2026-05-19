package com.hulkhiretech.payments.Service.impl;

import com.hulkhiretech.payments.Service.interfaces.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String createPayment() {
        return "Payment created successfully with ID = 12345";
    }

}
