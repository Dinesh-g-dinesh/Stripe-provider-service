package com.hulkhiretech.payments.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CreatePaymentReq {
    private String successUrl;
    private String cancelUrl;
    List<LineItem> lineItems;

}
