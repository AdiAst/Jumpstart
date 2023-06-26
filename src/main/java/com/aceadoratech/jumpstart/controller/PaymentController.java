package com.aceadoratech.jumpstart.controller;

import com.aceadoratech.jumpstart.entity.Transaction;
import com.aceadoratech.jumpstart.exchanges.PaymentRequest;
import com.aceadoratech.jumpstart.service.PaymentService;
import com.aceadoratech.jumpstart.service.TransactionService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
public class PaymentController {
    // services ==================================================
    private TransactionService transactionService;
    private PaymentService paymentService;
    private final String BASE_URL = "http://localhost:8080/api/payment";

    // payments ==================================================
    @PostMapping("/pay")
    public ResponseEntity<?> postPayment(@RequestBody PaymentRequest pay) {
        try {
            Payment payment = paymentService.payment(
                    pay.getTotal(),
                    "AUTHORIZE",
                    "Payment",
                    BASE_URL + "/canceled",
                    BASE_URL + "success");

            // add payment to transaction table
            transactionService.createTransaction(pay.getTransactionalRequest());

            return ResponseEntity.ok().body(payment.getLinks().get(1).getHref());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/success")
    public void success(@RequestParam String paymentId, @RequestParam String PayerID, HttpServletResponse response) {
        try {
            Payment payment = paymentService.pay(paymentId, PayerID);
            response.sendRedirect("http://localhost:3000/success");
        } catch (PayPalRESTException | IOException exception) {
            log.error(exception.getMessage());
        }
    }

    @GetMapping("/canceled")
    public ResponseEntity<?> oops() {
        return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_ACCEPTABLE);
    }
}
