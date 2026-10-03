package com.hulkhiretech.payments.controller;


import com.hulkhiretech.payments.Service.interfaces.PaymentService;
import com.hulkhiretech.payments.Stripe.CheckoutSessionResponse;
import com.hulkhiretech.payments.pojo.CreatePaymentReq;
import com.hulkhiretech.payments.pojo.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Payments", description = "APIs to create and manage payments via Stripe")
public class paymentController {

   private final PaymentService paymentService;

    @PostMapping
    @Operation(
            summary = "Create a new payment session",
            description = "Creates a Stripe checkout session for provided line items and returns a session id and host URL.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment session created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            }
    )
    public PaymentResponse createPayment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Create payment request payload", required = true, content = @Content(schema = @Schema(implementation = CreatePaymentReq.class)))
            @RequestBody CreatePaymentReq createPaymentReq) {
        log.info("Creating payment... CreatePaymentReq: {}", createPaymentReq);
        PaymentResponse paymentResponse = paymentService.createPayment(createPaymentReq);
        log.info("Payment creation response: {}", paymentResponse);
        // Implement payment creation logic here
        return paymentResponse;
    }

}
