package org.test.user.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.test.user.model.dom.UserDO;
import org.test.user.model.vo.UserVO;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE= Mappers.getMapper(UserConvert.class);
    UserVO convertVO(UserDO brandDO);
}
