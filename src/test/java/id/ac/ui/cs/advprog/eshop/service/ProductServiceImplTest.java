package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreate() {
        Product testProduct = new Product();
        testProduct.setProductId("TestId");
        testProduct.setProductName("TestName");
        testProduct.setProductQuantity(10);

        Mockito.when(productRepository.create(testProduct)).thenReturn(testProduct);
        service.create(testProduct);
    }

    @Test
    void testFindAll() {
        Product testProduct = new Product();
        testProduct.setProductId("TestId");
        testProduct.setProductName("TestName");
        testProduct.setProductQuantity(10);

        Mockito.when(productRepository.findAll()).thenReturn(List.of(testProduct).iterator());
        Iterator <Product> iterator = service.findAll().iterator();

        assertTrue(iterator.hasNext());
        Product inputProduct = iterator.next();
        assertEquals(inputProduct.getProductId(), testProduct.getProductId());
        assertEquals(inputProduct.getProductName(), testProduct.getProductName());
        assertEquals(inputProduct.getProductQuantity(), testProduct.getProductQuantity());
    }

    @Test
    void testEditProduct() {
        Product testProduct = new Product();
        testProduct.setProductId("TestId");
        testProduct.setProductName("TestName");
        testProduct.setProductQuantity(10);

        Mockito.when(productRepository.edit(testProduct)).thenReturn(testProduct);

        Product result = service.edit(testProduct);

        Mockito.verify(productRepository).edit(testProduct);
        assertEquals(testProduct, result);
        assertEquals(testProduct, result);
    }

    @Test
    void testDeleteProduct() {
        Product testProduct = new Product();
        testProduct.setProductId("TestId");
        testProduct.setProductName("TestName");
        testProduct.setProductQuantity(10);
        Mockito.when(productRepository.delete(testProduct)).thenReturn(testProduct);

        Product result = service.delete(testProduct);

        Mockito.verify(productRepository).delete(testProduct);
        assertEquals(testProduct, result);
        assertEquals(testProduct, result);
    }
}
