package org.example.springbootpaymentgatewayrazorpay.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.example.springbootpaymentgatewayrazorpay.Model.PaymentRequest;
import org.example.springbootpaymentgatewayrazorpay.Repository.PaymentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentRequest createOrder(PaymentRequest paymentRequest) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", paymentRequest.getAmount() * 100);
        orderRequest.put("currency", paymentRequest.getCurrency());
        orderRequest.put("receipt", paymentRequest.getEmail());

        Order order = razorpayClient.orders.create(orderRequest);
        System.out.println("Order created: " + order);
        paymentRequest.setRazorpayOrderId(order.get("id").toString());
        paymentRequest.setOrderStatus(order.get("status").toString());
        paymentRepository.save(paymentRequest);
        return paymentRequest;
    }

}
