package id.ac.ui.cs.advprog.eshop.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.OrderStatus;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

class PaymentTest {
    private List<Product> allProducts;
    private Order order;
    private List<Order> allOrders;

    @BeforeEach
    void setUp() {
        this.allProducts = new ArrayList<>();

        Product testProduct = new Product();

        testProduct.setProductId("d1c89c32-7904-4765-942a-7ea5200f6115");
        testProduct.setProductName("Sampo Cap Bambang");
        testProduct.setProductQuantity(2);

        Product testProduct1 = new Product();

        testProduct1.setProductId("39c9541f-1c82-4183-b2a4-fe9068b5387b");
        testProduct1.setProductName("Sampo Cap Udin");
        testProduct1.setProductQuantity(1);

        this.allProducts.add(testProduct);
        this.allProducts.add(testProduct1);


        this.allOrders = new ArrayList<>();
        Order testOrder = new Order("8f278bfd-cb0b-4afe-82aa-ee566807a63f", this.allProducts, 1708560000L, "Kamaru Udin", OrderStatus.SUCCESS.getValue());
        Order testOrder1 = new Order("509a4447-e2d4-4b04-97fa-fe842713eb5e", this.allProducts, 170856000L, "Kamaru Asep", OrderStatus.SUCCESS.getValue());
        this.allOrders.add(testOrder);
        this.allOrders.add(testOrder1);

        this.order = testOrder;
    }

    @Test
    void testDuplicatePayment() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "VOUCHER_CODE", this.allOrders.getFirst(), paymentData);

        assertSame(this.allOrders.getFirst(), payment.getOrder());
    }

    @Test
    void testPaymentInvalidPaymentMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "BISMILLAH", this.allOrders.getFirst(), paymentData);
        });
    }

    @Test
    void testRejectedPaymentEmptyData() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "VOUCHER_CODE", this.allOrders.getFirst(), paymentData);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "CASH_ON_DELIVERY", this.allOrders.getFirst(), paymentData);
        });
    }

    // Voucher Code
    @Test
    void testSuccessValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "VOUCHER_CODE", this.allOrders.getFirst(), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "VOUCHER_CODE", this.allOrders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeWrongPrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "EHEHE1234ABC5678");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "VOUCHER_CODE", this.allOrders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot8CharNumerical() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567A");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "VOUCHER_CODE", this.allOrders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    // Cash on Delivery
    @Test
    void testSuccessValidCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan-jalan");
        paymentData.put("deliveryFee", "17500");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "CASH_ON_DELIVERY", this.allOrders.getFirst(), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedCashOnDeliveryEmptyAddress() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("deliveryFee", "17500");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "CASH_ON_DELIVERY", this.allOrders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedCashOnDeliveryEmptyDeliveryFee() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan-jalan");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "CASH_ON_DELIVERY", this.allOrders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}
