package org.test.user.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.test.user.model.UserDO;
import org.test.user.vo.user.UserVO;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE= Mappers.getMapper(UserConvert.class);
    UserVO convertVO(UserDO brandDO);
}
