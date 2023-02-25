package financial.swap.challenge.client.github.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ContributorDto {
    private String login;
    private Long contributions;
}
