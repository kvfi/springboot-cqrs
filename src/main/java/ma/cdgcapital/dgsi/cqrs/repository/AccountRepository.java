package ma.cdgcapital.dgsi.cqrs.repository;

import ma.cdgcapital.dgsi.cqrs.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepository extends MongoRepository<Account, Long> {

    List<Account> findAll();
}
