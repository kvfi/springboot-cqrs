package ma.cdgcapital.dgsi.cqrs.services.queries;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final EventStore eventStore;

    public AccountQueryServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber).asStream().map(Message::getPayload).collect(Collectors.toList());
    }
}
