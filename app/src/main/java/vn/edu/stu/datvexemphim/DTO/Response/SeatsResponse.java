package vn.edu.stu.datvexemphim.DTO.Response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatsResponse implements Serializable {
    private int seat_id;

    private String seatType;

    private String seatRow;

    private int seatNumber;

    private Boolean isAvailable; // Giá trị mặc định ở mức ứng dụng

}
