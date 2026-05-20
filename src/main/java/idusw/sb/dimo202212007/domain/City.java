package idusw.sb.dimo202212007.domain;

import lombok.Builder;
import lombok.Data;


@Data       // @Getter, @Setter, @EqualAndHashCode, @ToString, @NoArgsConstrutor
@Builder    // Builder Pattern을 사용한 설정
public class City {
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;
}
