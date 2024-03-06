package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PaymentTest {
    private List<Map<String, String>> paymentsData;

    @BeforeEach
    void setUp(){
        paymentsData = new ArrayList<>();

        Map<String, String> validVoucher = new HashMap<>();
        validVoucher.put("voucherCode", "ESHOP12345678ABC");
        paymentsData.add(validVoucher);

        Map<String, String> invalidVoucher= new HashMap<>();
        invalidVoucher.put("voucherCode", "ESHOPINVALIDCODE123");
        paymentsData.add(invalidVoucher);

        Map<String, String> validCOD = new HashMap<>();
        validCOD.put("address", "Jakarta Selatan");
        validCOD.put("deliveryFee", "150000");
        paymentsData.add(validCOD);

        Map<String, String> invalidCOD = new HashMap<>();
        invalidCOD.put("address", "");
        invalidCOD.put("deliveryFee", "");
        paymentsData.add(invalidCOD);
    }

    @Test
    void testCreatePayment(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentsData.getFirst());
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals(paymentsData.getFirst(), payment.getPaymentData());
    }

    @Test
    void testCreatePaymentRejected(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentsData.get(1), "REJECTED");
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals(paymentsData.get(1), payment.getPaymentData());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccess(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentsData.getFirst(), "SUCCESS");
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals(paymentsData.getFirst(), payment.getPaymentData());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentStatusSuccess(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentsData.getFirst());
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentStatusRejected(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentsData.get(1));
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetPaymentStatusInvalid(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentsData.get(1));
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("FAILED"));
    }

    @Test
    void testCreateEmptyPayment(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE",  new HashMap<>());
        });
    }
    @Test
    void testCreatePaymentInvalidVoucherCode(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentsData.get(1));
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODValid(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", paymentsData.get(2));
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODInvalid(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", paymentsData.get(3));
        assertEquals("REJECTED", payment.getStatus());
    }
}
