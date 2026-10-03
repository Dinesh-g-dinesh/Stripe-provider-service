package com.hulkhiretech.payments.pojo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "An item in the order to be charged")
public class LineItem {

    @Schema(description = "Currency code in ISO 4217 format", example = "usd")
    private String currency;

    @Schema(description = "Name or description of the product/service", example = "T-Shirt")
    private String productName;

    @Schema(description = "Unit price in the smallest currency unit (e.g., cents)", example = "1500")
    private int unitAmount;

    @Schema(description = "Quantity of this item", example = "2")
    private int quantity;
}
