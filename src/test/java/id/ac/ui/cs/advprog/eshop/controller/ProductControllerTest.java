package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @BeforeEach
    void setup(){}

    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @Test
    void testProductListPage() {
        Model model = mock(Model.class);
        String template = productController.productListPage(model);
        assertEquals("productList", template);
    }

    @Test
    void testCreateProductGet() {
        Model model = mock(Model.class);
        String template = productController.createProductPage(model);
        assertEquals("createProduct", template);
    }

    @Test
    void testCreateProductPost() {
        Model model = mock(Model.class);
        Product testProduct = new Product();
        Mockito.when(productService.create(testProduct)).thenReturn(testProduct);
        String template = productController.createProductPost(testProduct, model);
        assertEquals("redirect:list", template);
    }

    @Test
    void testEditProductPage() {
        Model model = mock(Model.class);
        Product testProduct = new Product();
        String template = productController.editProductPage(model, "testId");
        assertEquals("editProduct", template);
    }

    @Test
    void testEditProductPost() {
        Model model = mock(Model.class);
        Product testProduct = new Product();
        String template = productController.editProductPost(testProduct, model, "testId");
        assertEquals("redirect:../list", template);
    }

    @Test
    void testDeleteProduct() {
        Model model = mock(Model.class);
        Product testProduct = new Product();
        testProduct.setProductId("testId");
        Mockito.when(productService.delete(testProduct)).thenReturn(testProduct);
        String result = productController.deleteProductGet(testProduct, model,"testId");
        assertEquals("redirect:../list", result);
    }
}
