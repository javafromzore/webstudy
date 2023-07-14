package org.test.user.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.test.user.model.CollectDO;
import org.test.user.vo.collect.CollectVO;

@Mapper
public interface CollectConvert {
    CollectConvert INSTANCE= Mappers.getMapper(CollectConvert.class);
    CollectVO convertVo(CollectDO collectDO);
}
