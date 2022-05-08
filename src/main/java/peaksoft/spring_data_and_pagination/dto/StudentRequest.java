package peaksoft.spring_data_and_pagination.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StudentRequest {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String address;
    private Long course;
}
