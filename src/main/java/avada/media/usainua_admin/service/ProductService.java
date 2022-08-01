package avada.media.usainua_admin.service;


import avada.media.usainua_admin.model.Product;
import avada.media.usainua_admin.model.order.Order;
import org.springframework.data.domain.Page;

public interface ProductService {

    void saveProduct(Product product);

    void deleteProductById(Long id);

    Page<Product> getPageProducts(int pageNumber, int pageSize, String sortBy, String sortDirection);

    Page<Product> getPageProductsByUserId(Long id, int pageNumber, int pageSize, String sortBy, String sortDirection);

    Product getProductById(Long id);

    Long countAllProducts();

}
