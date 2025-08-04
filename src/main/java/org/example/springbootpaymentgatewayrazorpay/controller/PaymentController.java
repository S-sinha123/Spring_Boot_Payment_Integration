package org.example.springbootpaymentgatewayrazorpay.controller;

import com.razorpay.RazorpayException;

import java.util.UUID;

import org.example.springbootpaymentgatewayrazorpay.Model.PaymentRequest;
import org.example.springbootpaymentgatewayrazorpay.service.RazorpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private RazorpayService razorpayService;

    @GetMapping(value = "/payment-form")
    public String getPaymentForm() {
        return "index"; // This will return the payment.html template
    }

    @PostMapping(value = "/create-order", produces = "application/json")
    public ResponseEntity<PaymentRequest> createOrder(@RequestBody PaymentRequest paymentRequest) {

        try {
            PaymentRequest pRequest = razorpayService.createOrder(paymentRequest);
            return new ResponseEntity<>(pRequest, HttpStatus.CREATED);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }

}