package financial.swap.challenge.rabbit.msg;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatsMsg {
    private String username;
    private String repository;
}
