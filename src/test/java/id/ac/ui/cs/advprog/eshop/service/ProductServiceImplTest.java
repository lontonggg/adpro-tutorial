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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Product 1");
        product.setProductQuantity(1);

        Mockito.when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("123");
        product1.setProductName("Product 1");
        product1.setProductQuantity(1);

        Product product2 = new Product();
        product2.setProductId("456");
        product2.setProductName("Product 2");
        product2.setProductQuantity(2);

        productList.add(product1);
        productList.add(product2);

        Iterator<Product> productIterator = productList.iterator();
        Mockito.doReturn(productIterator).when(productRepository).findAll();

        Iterator<Product> foundProducts = productService.findAll().iterator();
        assertTrue(foundProducts.hasNext());
        Product checkProduct = foundProducts.next();
        assertEquals(productList.getFirst(), checkProduct);
        Product nextProduct = foundProducts.next();
        assertEquals(productList.get(1), nextProduct);
    }

    @Test
    void testFindProductById() {
        String productId = "123";
        Product product = new Product();
        Mockito.when(productRepository.findProductById(productId)).thenReturn(product);

        Product foundProduct = productService.findProductById(productId);

        assertEquals(product, foundProduct);
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("123");

        Mockito.doNothing().when(productRepository).delete(product);
        productService.delete(product);

        assertNull(productService.findProductById("123"));
    }

    @Test
    void testEdit() {
        Product currentProduct = new Product();
        currentProduct.setProductId("123");
        currentProduct.setProductName("Product");
        currentProduct.setProductQuantity(1);

        Product editedProduct = new Product();
        editedProduct.setProductId("456");
        editedProduct.setProductName("Edited Product");
        editedProduct.setProductQuantity(2);

        Mockito.when(productRepository.create(currentProduct)).thenReturn(currentProduct);
        Mockito.doNothing().when(productRepository).edit(currentProduct, editedProduct);

        productService.create(currentProduct);
        productService.edit(currentProduct, editedProduct);

        assertNotEquals(currentProduct.getProductId(), editedProduct.getProductId());
        assertNotEquals(currentProduct.getProductName(), editedProduct.getProductName());
        assertNotEquals(currentProduct.getProductQuantity(), editedProduct.getProductQuantity());
    }
}