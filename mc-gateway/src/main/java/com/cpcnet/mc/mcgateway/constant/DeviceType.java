package com.cpcnet.mc.mcgateway.constant;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Description: 设备枚举
 * @Author: Ebon Zheng
 * @Date: 2023/8/1
 */
public enum DeviceType {
    PC(1), IPHONE(2), ANDROID_PHONE(3), IPAD(4), ANDROID_PAD(5), OTHER(-1);

    private int code;

    DeviceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static boolean contains(int code) {
        return Arrays.stream(DeviceType.values())
                .map(DeviceType::getCode)
                .distinct().anyMatch(s -> Objects.equals(s, code));
    }

}
