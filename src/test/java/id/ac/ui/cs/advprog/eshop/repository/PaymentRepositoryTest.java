package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Map<String, String>> paymentData;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();
        paymentData = new ArrayList<>();

        Map<String, String> validVoucher = new HashMap<>();
        validVoucher.put("voucherCode", "ESHOP12345678ABC");
        paymentData.add(validVoucher);

        Map<String, String> validCOD = new HashMap<>();
        validCOD.put("address", "Jakarta Selatan");
        validCOD.put("deliveryFee", "150000");
        paymentData.add(validCOD);

        Payment paymentVoucher = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData.getFirst());
        payments.add(paymentVoucher);

        Payment paymentCOD = new Payment("a2c62328-4a37-4664-83c7-f32db8620155", "CASH_ON_DELIVERY", paymentData.get(1));
        payments.add(paymentCOD);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.getFirst();
        Payment savedPayment = paymentRepository.save(payment);

        Payment paymentFromRepository= paymentRepository.findById(payments.getFirst().getId());
        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getId(), paymentFromRepository.getId());
        assertEquals(payment.getMethod(), paymentFromRepository.getMethod());
        assertEquals(payment.getStatus(), paymentFromRepository.getStatus());
        assertSame(payment.getPaymentData(), paymentFromRepository.getPaymentData());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), "VOUCHER_CODE", paymentData.getFirst());
        Payment savedPayment = paymentRepository.save(newPayment);

        Payment paymentFromRepository = paymentRepository.findById(payments.getFirst().getId());
        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getId(), paymentFromRepository.getId());
        assertEquals(payment.getMethod(), paymentFromRepository.getMethod());
        assertEquals(payment.getStatus(), paymentFromRepository.getStatus());
        assertSame(payment.getPaymentData(), paymentFromRepository.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment paymentFromRepository = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), paymentFromRepository.getId());
        assertEquals(payments.get(1).getMethod(), paymentFromRepository.getMethod());
        assertEquals(payments.get(1).getStatus(), paymentFromRepository.getStatus());
        assertSame(payments.get(1).getPaymentData(), paymentFromRepository.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment  paymentFromRepository = paymentRepository.findById("zczc");
        assertNull(paymentFromRepository);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment>  paymentFromRepository = paymentRepository.findAll();
        assertEquals(payments.size(),  paymentFromRepository.size());
        for (int i = 0; i < payments.size(); i++) {
            assertEquals(payments.get(i).getId(),  paymentFromRepository.get(i).getId());
            assertEquals(payments.get(i).getMethod(), paymentFromRepository.get(i).getMethod());
            assertEquals(payments.get(i).getStatus(),  paymentFromRepository.get(i).getStatus());
            assertSame(payments.get(i).getPaymentData(),  paymentFromRepository.get(i).getPaymentData());
        }
    }
}
