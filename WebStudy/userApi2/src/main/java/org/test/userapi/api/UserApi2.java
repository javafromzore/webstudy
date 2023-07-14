package org.test.userapi.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.test.common.model.vo.Result;


@FeignClient(name = "user2", path = "user2/user")
public interface UserApi2 {
    @GetMapping("/ticketSeller")
    Result ticketSeller();
}
