package hotelcms.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hotelcms.config.kafka.KafkaProcessor;
import hotelcms.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    RoomRepository roomRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CheckInInfoRegistered'"
    )
    public void wheneverCheckInInfoRegistered_CheckinStatusUpdate(
        @Payload CheckInInfoRegistered checkInInfoRegistered
    ) {
        CheckInInfoRegistered event = checkInInfoRegistered;
        System.out.println(
            "\n\n##### listener CheckinStatusUpdate : " +
            checkInInfoRegistered +
            "\n\n"
        );
        // Sample Logic //

    }
}
//>>> Clean Arch / Inbound Adaptor
