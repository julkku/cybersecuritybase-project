package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
