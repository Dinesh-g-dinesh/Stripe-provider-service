package com.hulkhiretech.payments.controller;


import com.hulkhiretech.payments.Service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
@RequiredArgsConstructor
public class paymentController {

   private final PaymentService paymentService;

    @PostMapping
    public String createPayment() {
        log.info("Creating payment...");
        String response = paymentService.createPayment();
        log.info("Payment creation response: {}", response);
        // Implement payment creation logic here
        return "Payment created successfully : " + response;
    }

}
