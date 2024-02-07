package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findProductById(String id){
        for(Product product: productData){
            if(product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }

    public void edit(Product currentProduct, Product editedProduct){
        for(Product product: productData){
            if(product.equals(currentProduct)){
                productData.remove(product);
                productData.add(editedProduct);
                return;
            }
        }
    }

    public void delete(Product product){
        productData.remove(product);
    }
}
