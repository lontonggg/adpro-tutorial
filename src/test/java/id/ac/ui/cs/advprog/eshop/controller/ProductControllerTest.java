package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @BeforeEach
    void setup(){
    }

    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @Test
    void testCreateProductPage(){
        Model model = mock(Model.class);
        String result = productController.createProductPage(model);
        assertEquals("createProduct", result);
    }

    @Test
    void testCreateProductPost(){
        Model model = mock(Model.class);

        Product product = new Product();
        Mockito.when(productService.create(product)).thenReturn(product);

        String result = productController.createProductPost(product, model);
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testProductListPage(){
        Model model = mock(Model.class);

        List<Product> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);

        String result = productController.productListPage(model);
        assertEquals("productList", result);
    }

    @Test
    void testHomePage(){
        String result = productController.homePage();
        assertEquals("homePage", result);
    }

    @Test
    void testEditProductPage(){
        Model model = mock(Model.class);

        Product product = new Product();
        String productId = "123";
        product.setProductId(productId);

        Mockito.when(productService.findProductById(productId)).thenReturn(product);

        String result = productController.editProductPage(model, productId);
        assertEquals("editProduct", result);
    }

    @Test
    void testEditProductPost(){
        Model model = mock(Model.class);

        Product product = new Product();
        String productId = "123";
        product.setProductId(productId);

        Product editedProduct = new Product();
        String editedProductId = "456";
        editedProduct.setProductId(editedProductId);

        Mockito.when(productService.findProductById(productId)).thenReturn(product);
        Mockito.doNothing().when(productService).edit(product, editedProduct);

        String result = productController.editProductPost(editedProduct, model, productId);
        assertEquals("redirect:../list", result);
    }

    @Test
    void testDelete(){
        Model model = mock(Model.class);

        Product product = new Product();
        String productId = "123";
        product.setProductId(productId);

        Mockito.when(productService.findProductById(productId)).thenReturn(product);

        String result = productController.delete(model, productId);
        assertEquals("redirect:/product/list", result);
    }
}
