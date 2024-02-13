package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator <Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator <Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator <Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteEmptyProductList() {
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());

        Product deleteProduct = new Product();
        deleteProduct.setProductId("test");
        productRepository.delete(deleteProduct);

        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteOneOfOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product deleteProduct = new Product();
        deleteProduct.setProductId(product1.getProductId());
        productRepository.delete(deleteProduct);

        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteOneOfOneProductNotFound() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product targetProduct = new Product();
        targetProduct.setProductId("IdNotFound");
        productRepository.delete(targetProduct);

        assertTrue(iterator.hasNext());
        Product notDeletedProduct = iterator.next();
        assertEquals(notDeletedProduct.getProductId(), product1.getProductId());

        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteOneOfManyProducts() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Product2");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        Product targetProduct = new Product();
        targetProduct.setProductId(product2.getProductId());
        productRepository.delete(targetProduct);

        assertFalse(iterator.hasNext());
    }

    @Test
    void deleteOneOfManyProductsNotFound() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Product2");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        Product targetProduct = new Product();
        targetProduct.setProductId("Id not Found");
        productRepository.delete(targetProduct);

        assertTrue(iterator.hasNext());
    }

    @Test
    void testDeleteAllOfManyProducts() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Product2");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product targetProduct1 = new Product();
        targetProduct1.setProductId(product1.getProductId());
        productRepository.delete(targetProduct1);

        assertTrue(iterator.hasNext());

        Product targetProduct2 = new Product();
        targetProduct2.setProductId(product2.getProductId());
        productRepository.delete(targetProduct2);

        assertFalse(iterator.hasNext());
    }

    @Test
    void testEditNonExistentProductFromEmptyList() {
        Product targetProduct = new Product();
        targetProduct.setProductId("test");
        targetProduct.setProductName("test target");
        targetProduct.setProductQuantity(10);

        Product editedProduct = productRepository.edit(targetProduct);
        assertNull(editedProduct);
    }

    @Test
    void testEditProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product targetProduct = new Product();
        targetProduct.setProductId(product1.getProductId());
        targetProduct.setProductName("UpdatedProduct1");
        targetProduct.setProductQuantity(11);

        Product editedProduct = productRepository.edit(targetProduct);
        assertEquals(editedProduct.getProductId(), targetProduct.getProductId());
        assertEquals(editedProduct.getProductName(), targetProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), targetProduct.getProductQuantity());
    }

    @Test
    void testEditProductFromNotEmptyListNotFound() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Product1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product targetProduct = new Product();
        targetProduct.setProductId("Id Not Found");
        targetProduct.setProductName("UpdatedProduct1");
        targetProduct.setProductQuantity(11);

        Product editedProduct = productRepository.edit(targetProduct);
        assertNull(editedProduct);
    }
}