package org.test.userapi.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.test.common.model.vo.Result;


@FeignClient(name = "user", path = "user/user")
public interface UserApi {
    @GetMapping("/ticketSeller")
    Result ticketSeller();
}
