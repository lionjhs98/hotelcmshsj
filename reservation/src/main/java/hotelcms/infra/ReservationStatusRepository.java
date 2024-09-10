package hotelcms.infra;

import hotelcms.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "reservationStatuses",
    path = "reservationStatuses"
)
public interface ReservationStatusRepository
    extends PagingAndSortingRepository<ReservationStatus, Long> {}
