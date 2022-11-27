package platform.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.Code;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    Code findCodeById(UUID id);

    @Query(value = "SELECT * FROM CODE WHERE IS_TIME_RESTRICTED IS FALSE AND IS_VIEW_RESTRICTED IS FALSE ORDER BY DATE DESC LIMIT 10", nativeQuery = true)
    List<Code> notRestricted();

    @Modifying
    @Transactional
    void deleteByToBeDeletedIsTrue();

    @Modifying
    @Transactional
    void deleteById(UUID id);
}