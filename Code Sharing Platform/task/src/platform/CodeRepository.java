package platform;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    Code findCodeById(UUID id);

    List<Code> findFirst10ByOrderByIdDesc();

    List<Code> findFirst10ByOrderByDateDesc();

    //List<Code> findFirst10ByTimeRestrictedIsFalseAndViewRestrictedIsFalseOrderByDateDesc();
    @Query(value = "SELECT * FROM CODE WHERE IS_TIME_RESTRICTED IS FALSE AND IS_VIEW_RESTRICTED IS FALSE ORDER BY DATE DESC LIMIT 10", nativeQuery = true)
    List<Code> test();


    @Modifying
    @Transactional
    void deleteByToBeDeletedIsTrue();

}