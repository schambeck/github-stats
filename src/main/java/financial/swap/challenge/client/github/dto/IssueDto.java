package financial.swap.challenge.client.github.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IssueDto {
    private String url;
    private String title;
    private UserDto user;
    private List<LabelDto> labels;
}
