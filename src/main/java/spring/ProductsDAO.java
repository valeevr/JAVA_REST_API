package spring;

import org.springframework.stereotype.Repository;

@Repository
public class ProductsDAO
{
    private static Products list = new Products();

    static
    {
        list.getProductList().add(new Product(1, "milk"));
        list.getProductList().add(new Product(2, "bread"));
        list.getProductList().add(new Product(3, "meat"));
        list.getProductList().add(new Product(4, "fish"));
        list.getProductList().add(new Product(5, "juice"));
    }

    public Products getAllProducts()
    {
        return list;
    }

    public void addProduct(Product product) {
        list.getProductList().add(product);
    }
}
