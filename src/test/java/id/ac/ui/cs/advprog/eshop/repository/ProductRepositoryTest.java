package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct(){
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

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct(){
        Product product = new Product();
        product.setProductId("12345");
        product.setProductName("Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("54321");
        editedProduct.setProductName("Edited Product");
        editedProduct.setProductQuantity(50);
        productRepository.edit(product, editedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertNotNull(productRepository.findProductById(editedProduct.getProductId())); // Ensure the edited product exist in repository
        assertNull(productRepository.findProductById(product.getProductId())); // Ensure the pre-edited product is no longer in the repository
    }

    @Test
    void testEditNonExistingProduct(){
        Product product = new Product();
        product.setProductId("12345");
        product.setProductName("Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product nonExistingProduct = new Product();
        nonExistingProduct.setProductId("54321");
        nonExistingProduct.setProductName("Non Existing Product");
        nonExistingProduct.setProductQuantity(50);

        Product editedNonExistingProduct = new Product();
        editedNonExistingProduct.setProductId("99999");
        editedNonExistingProduct.setProductName("Edited Non Existing Product");
        editedNonExistingProduct.setProductQuantity(25);
        productRepository.edit(nonExistingProduct, editedNonExistingProduct);

        assertNull(productRepository.findProductById(editedNonExistingProduct.getProductId())); // Ensure non-existing product does not exist in the repository
        assertNotNull(productRepository.findProductById(product.getProductId())); // Ensure unedited product still exist in the repository
    }

    @Test
    void testDeleteProduct(){
        Product product = new Product();
        product.setProductId("12345");
        product.setProductName("Product");
        product.setProductQuantity(100);

        productRepository.create(product);
        productRepository.delete(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertNull(productRepository.findProductById(product.getProductId())); // Ensure the product no longer exist in the repository
        assertFalse(productIterator.hasNext()); // Ensure the repository is empty
    }

    @Test
    void testDeleteNonExistingProduct(){
        Product product = new Product();
        product.setProductId("12345");
        product.setProductName("Product");
        product.setProductQuantity(100);

        Product nonExistingProduct = new Product();
        nonExistingProduct.setProductId("54321");
        nonExistingProduct.setProductName("Non Existing Product");
        nonExistingProduct.setProductQuantity(50);

        productRepository.create(product);
        productRepository.delete(nonExistingProduct);

        assertNotNull(productRepository.findProductById(product.getProductId())); // Ensure the non-deleted product still exist in the repository
        assertNull(productRepository.findProductById(nonExistingProduct.getProductId())); // Ensure that the non-existent product is not in the repository
    }
}
