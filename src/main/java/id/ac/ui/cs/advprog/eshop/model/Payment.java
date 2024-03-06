package id.ac.ui.cs.advprog.eshop.model;
import enums.OrderStatus;

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
        String[] methodList = {"VOUCHER_CODE", "CASH_ON_DELIVERY"};

        if (Arrays.stream(methodList).noneMatch(item -> item.equals(method))) {
            throw new IllegalArgumentException("Invalid payment method");
        }

        this.method = method;
        this.order = order;

        this.paymentData = paymentData;
        if (method.equals("VOUCHER_CODE")) {
            this.status = verifyCode();
        }
        else if (method.equals("CASH_ON_DELIVERY")) {
            this.status = verifyCode();
        }
    }

    public void setStatus(String status) {
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
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numCount += 1;
            }
        }
        if (numCount != 8) {
            return "REJECTED";
        }

        return "SUCCESS";
    }
}
