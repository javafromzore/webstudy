package org.test.user.vo.collect;

import lombok.Data;
import org.test.car.vo.CarVO;

import java.util.List;

@Data
public class DetailVO {
    private CollectVO collectVO;
    private List<CarVO> carVOS;
}
