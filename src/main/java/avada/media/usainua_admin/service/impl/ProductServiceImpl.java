package avada.media.usainua_admin.service.impl;

import avada.media.usainua_admin.model.Product;
import avada.media.usainua_admin.repo.ProductRepo;
import avada.media.usainua_admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.deleteProductById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found with id: " + id)
        );
    }

    @Override
    public Page<Product> getPageProducts(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return productRepo.findAll(PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Transactional
    @Override
    public Page<Product> getPageProductsByUserId(Long id, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        return productRepo.findByUserId(id, PageRequest.of(pageNumber, pageSize, sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending()));
    }

    @Override
    public Long countAllProducts() {
        return productRepo.count();
    }

}
