package in.das.repository;

import in.das.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUsername(final String username);

    Optional<Account> findByEmailId(final String email);

    @Query("select acc from Account acc where acc.username = :username and acc.password = :password")
    Optional<Account> findByUsernameAndPassword(final @Param("username") String username, final @Param("password") String password);
}
