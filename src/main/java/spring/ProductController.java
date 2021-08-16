package spring;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/data")
public class ProductController
{
    @Autowired
    private ProductsDAO productsDAO;

    @GetMapping(path="", produces = "application/json")
    public Products getProducts()
    {
        return productsDAO.getAllProducts();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id)
    {
        String notFound = String.format("item with id: %d not found", id);
        return productsDAO
                .getAllProducts().getProductList().stream()
                        .filter(product -> id.equals(product.getId()))
                        .findFirst()
                        .<ResponseEntity<Object>>map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND));
    }

    @PostMapping(path= "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addProduct(@RequestBody Product product)
    {
        Integer id = productsDAO.getAllProducts().getProductList().size() + 1;
        product.setId(id);

        productsDAO.addProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}