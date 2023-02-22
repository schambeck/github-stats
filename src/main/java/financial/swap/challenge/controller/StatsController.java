package financial.swap.challenge.controller;

import financial.swap.challenge.rabbit.msg.StatsMsg;
import financial.swap.challenge.rabbit.StatsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stats")
@RequiredArgsConstructor
class StatsController {
    @Value("${custom.github.username}")
    String username;
    @Value("${custom.github.repository}")
    String repository;
    final StatsProducer producer;

    @PostMapping
    ResponseEntity<Void> create() {
        StatsMsg msg = StatsMsg.builder()
                .username(username)
                .repository(repository)
                .build();
        producer.sendMessage(msg);
        return ResponseEntity.accepted().build();
    }
}
