package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.Product;
import avada.media.usainua_admin.model.user.PersonalData;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);
    Page<Product> findByUserId(Long id, Pageable pageable);

}
