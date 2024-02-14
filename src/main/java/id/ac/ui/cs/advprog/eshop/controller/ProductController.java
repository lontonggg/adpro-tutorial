package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/product/edit/{id}")
    public String editProductPage(Model model, @PathVariable("id") String id){
        Product product = service.findProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }
    @GetMapping("/product/delete/{id}")
    public String delete(Model model, @PathVariable("id") String id) {
        Product product = service.findProductById(id);
        service.delete(product);
        return "redirect:/product/list";
    }

    @PostMapping("/product/edit/{id}")
    public String editProductPost(@ModelAttribute Product editedProduct, Model model, @PathVariable("id") String id){
        Product currentProduct = service.findProductById(id);
        service.edit(currentProduct, editedProduct);
        return "redirect:../list";
    }
}
