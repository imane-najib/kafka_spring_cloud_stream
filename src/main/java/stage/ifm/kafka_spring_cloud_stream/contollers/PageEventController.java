package stage.ifm.kafka_spring_cloud_stream.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import stage.ifm.kafka_spring_cloud_stream.events.PageEvent;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventController {
    @Autowired
    private StreamBridge streamBridge;
    @GetMapping("/publish")
    public PageEvent publish(String name,String topic) {
        PageEvent pageEvent = new PageEvent(
                name,
                Math.random()>0.5?"U1":"U2",
                new Date(),
                10+new Random().nextInt(10000));
        streamBridge.send(topic, pageEvent);
        return pageEvent;
    }
}
