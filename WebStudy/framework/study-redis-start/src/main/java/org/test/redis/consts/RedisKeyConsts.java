package org.test.redis.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedisKeyConsts {
    GOODS_COUNT("g_c"),

    GOODS_COUNT_LOCK("g_c_l");
    private final String Key;
}