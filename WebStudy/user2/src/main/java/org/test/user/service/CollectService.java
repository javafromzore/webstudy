package org.test.user.service;

import org.test.user.vo.collect.DetailVO;
import org.test.user.vo.user.UserVO;

public interface CollectService {
    DetailVO getDetailByUserId(long userId);
}
