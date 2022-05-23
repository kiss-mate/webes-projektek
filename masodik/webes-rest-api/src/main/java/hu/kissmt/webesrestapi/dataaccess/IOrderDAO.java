package hu.kissmt.webesrestapi.dataaccess;

import hu.kissmt.webesrestapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDAO extends JpaRepository<Order, Long> {
}
