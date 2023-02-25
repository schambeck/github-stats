package financial.swap.challenge.client.github.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class IssueDto {
    private String title;
    private UserDto user;
    private List<LabelDto> labels;
}
