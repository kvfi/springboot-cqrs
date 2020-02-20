package ma.cdgcapital.dgsi.cqrs.services.queries;

import java.util.List;

public interface AccountQueryService {
    List<Object> listEventsForAccount(String accountNumber);
}
