package id.ac.ui.cs.advprog.eshop.model;
import enums.PaymentMethod;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


import lombok.Getter;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Order order;
    Map<String, String> paymentData;


    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;

        this.paymentData = paymentData;
        if (method.equals("VOUCHER_CODE")) {
            this.status = verifyCode();
        } else if (method.equals("CASH_ON_DELIVERY")) {
            this.status = verifyCode();
        }

        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid method");
        }
        this.method = method;

        this.order = order;
        this.paymentData = paymentData;

        if (status == null) {
            setStatus();
        }
    }

    public void setStatus(String status) {
        if (this.method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            if (!this.paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException("Invalid payment data for current method");
            }
            this.status = verifyCode();
        } else if (this.method.equals(PaymentMethod.CASH_ON_DELIVERY.getValue())) {
            if (!this.paymentData.containsKey("address") ||
                    !this.paymentData.containsKey("deliveryFee")) {
                throw new IllegalArgumentException("Invalid payment data for current method");
            }
            this.status = verifyCashOnDelivery();
        }
    }

    public Order getOrder() {
        return null;
    }

    private String verifyCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return "REJECTED";
        }

        if (voucherCode.length() != 16) {
            return "REJECTED";
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return "REJECTED";
        }

        int numCount = 0;
        for (char character : voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numCount += 1;
            }
        }
        if (numCount != 8) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    private String verifyCashOnDelivery() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        if (address == null || address.isEmpty()) {
            return "REJECTED";
        }

        if (deliveryFee == null || deliveryFee.isEmpty()) {
            return "REJECTED";
        }

        return "SUCCESS";
    }
}