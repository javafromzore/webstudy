package org.test.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.car.api.CarApi;
import org.test.car.vo.CarVO;
import org.test.common.model.dto.IdDTO;
import org.test.common.model.vo.Result;
import org.test.common.util.ArrayUtil;
import org.test.user.convert.CollectConvert;
import org.test.user.dao.CollectCarMapper;
import org.test.user.dao.CollectMapper;
import org.test.user.model.CollectDO;
import org.test.user.vo.collect.DetailVO;
import org.test.user.service.CollectService;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private CollectCarMapper collectCarMapper;
    @Autowired
    private CarApi carApi;

    @Override
    public DetailVO getDetailByUserId(long userId) {
        CollectDO collectDO = collectMapper.getByUserId(userId);
        IdDTO idDTO = new IdDTO();
        long[] longs = collectCarMapper.listCarIdByCollectId(collectDO.getId());
        idDTO.setIds(ArrayUtil.longToString(longs));
        List<CarVO> carVOS = carApi.listByIds(idDTO).getData();
        DetailVO detailVO = new DetailVO();
        detailVO.setCarVOS(carVOS);
        detailVO.setCollectVO(CollectConvert.INSTANCE.convertVo(collectDO));
        return detailVO;
    }
}