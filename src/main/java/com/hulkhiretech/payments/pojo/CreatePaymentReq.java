package com.hulkhiretech.payments.pojo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Data
@Schema(description = "Request payload to create a payment / checkout session")
public class CreatePaymentReq {

    @Schema(description = "URL where the customer will be redirected after successful payment", example = "https://example.com/success")
    private String successUrl;

    @Schema(description = "URL where the customer will be redirected if they cancel payment", example = "https://example.com/cancel")
    private String cancelUrl;

    @Schema(description = "A list of line items describing products/services to charge")
    List<LineItem> lineItems;

}
