package peaksoft.spring_data_and_pagination.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponseView {

    private List<StudentResponse>studentResponses;
}
