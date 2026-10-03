package com.hulkhiretech.payments.pojo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Response returned after creating a payment session")
public class PaymentResponse {

    @Schema(description = "Stripe checkout session identifier", example = "cs_test_a1b2c3d4")
    private String sessionId;

    @Schema(description = "URL of the payment host where the customer completes checkout", example = "https://checkout.stripe.com/pay/cs_test_a1b2c3d4")
    private String hostUrl;
}
