package financial.swap.challenge.rabbit.msg;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class StatsMsg {
    private String username;
    private String repository;
}
