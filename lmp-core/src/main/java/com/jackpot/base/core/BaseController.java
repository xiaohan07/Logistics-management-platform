package com.jackpot.base.core;

import com.jackpot.base.base.BaseUtil;
import com.jackpot.base.core.exception.ParamsException;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public void validateParams(Object obj, String... args) {
        if (obj == null) return;
        try {
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (BaseUtil.isEmpty(value)) {
                    if (ArrayUtils.isEmpty(args)) {
                        throw new ParamsException("缺少参数[%s]", field.getName());
                    } else {
                        for (String arg : args) {
                            if (arg.equals(field.getName())) throw new ParamsException("缺少参数[%s]", field.getName());
                        }
                    }
                }
            }
        }catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
