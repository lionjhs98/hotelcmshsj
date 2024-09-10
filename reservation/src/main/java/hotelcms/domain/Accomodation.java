package hotelcms.domain;

import hotelcms.ReservationApplication;
import hotelcms.domain.CheckInInfoRegistered;
import hotelcms.domain.CheckoutInfoRegistered;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Accomodation_table")
@Data
//<<< DDD / Aggregate Root
public class Accomodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accomodationId;

    private Long roomId;

    @Embedded
    private DateTime checkInTime;

    @Embedded
    private DateTime checkOutTime;

    @PostPersist
    public void onPostPersist() {
        CheckoutInfoRegistered checkoutInfoRegistered = new CheckoutInfoRegistered(
            this
        );
        checkoutInfoRegistered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        hotelcms.external.Housekeeping housekeeping = new hotelcms.external.Housekeeping();
        // mappings goes here
        ReservationApplication.applicationContext
            .getBean(hotelcms.external.HousekeepingService.class)
            .assignHousekeeper(housekeeping);

        CheckInInfoRegistered checkInInfoRegistered = new CheckInInfoRegistered(
            this
        );
        checkInInfoRegistered.publishAfterCommit();
    }

    public static AccomodationRepository repository() {
        AccomodationRepository accomodationRepository = ReservationApplication.applicationContext.getBean(
            AccomodationRepository.class
        );
        return accomodationRepository;
    }
}
//>>> DDD / Aggregate Root
