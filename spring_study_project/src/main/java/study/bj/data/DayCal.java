package study.bj.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="calendar")
public class DayCal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userid;

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String startdate;

    @Column
    private String starttime;

    @Column
    private String enddate;

    @Column
    private String endtime;
}
