package hu.kissmt.webesrestapi.dataaccess;

import hu.kissmt.webesrestapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDAO extends JpaRepository<Product, Long> {
}
