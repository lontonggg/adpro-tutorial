package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String status;
    String method;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData){
        this.id = id;
        this.method = method;

        if(paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            if(method.equals("VOUCHER_CODE")){
                String code = paymentData.get("voucherCode");
                int numericalCount = 0;
                for (int i = 5; i < code.length(); i++) {
                    if (Character.isDigit(code.charAt(i))) {
                        numericalCount++;
                    }
                }
                if (code.length() != 16 || !code.startsWith("ESHOP") || numericalCount <= 8) {
                    this.status = PaymentStatus.REJECTED.getValue();
                } else{
                    this.status = PaymentStatus.SUCCESS.getValue();
                }
            } else if(method.equals("CASH_ON_DELIVERY")){
                if(paymentData.get("address") == null || paymentData.get("address").isEmpty() || paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()){
                    this.status = PaymentStatus.REJECTED.getValue();
                } else {
                    this.status = PaymentStatus.SUCCESS.getValue();
                }
            }
            this.paymentData = paymentData;
        }
    }

    public Payment(String id, String method, Map<String, String> paymentData, String status){
        this.id = id;
        this.paymentData = paymentData;
        this.status = status;
        this.method = method;
    }

    public void setStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
