package study.bj.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreCal {
    private Integer id;
    private String title;
    private String content;
    private String startdate;
    private String starttime;
    private String enddate;
    private String endtime;
}
