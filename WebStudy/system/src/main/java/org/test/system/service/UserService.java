package org.test.system.service;

import org.test.system.pojo.dto.LoginDTO;
import org.test.system.pojo.vo.TokenVO;

public interface UserService {
    TokenVO login(LoginDTO dto);
}
