package org.example.springbootpaymentgatewayrazorpay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.springbootpaymentgatewayrazorpay.Model.PaymentRequest;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentRequest, Long> {
    // This interface will automatically provide CRUD operations for PaymentRequest
    // 
    // entities
}
