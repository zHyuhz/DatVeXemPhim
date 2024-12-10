package vn.edu.stu.datvexemphim.DTO.Response;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)

public class UserResponse implements Serializable {
    int user_id;
    String fullName;
    String birthday;
    int gender;
    String city;
    String phoneNumber;

}
