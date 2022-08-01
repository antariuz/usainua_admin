package avada.media.usainua_admin.repo;

import avada.media.usainua_admin.model.BankingCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankingCardRepo extends JpaRepository<BankingCard, Long> {
}
