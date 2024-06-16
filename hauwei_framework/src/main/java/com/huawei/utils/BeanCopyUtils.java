package com.huawei.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){

    }
    public static <V> V copyBean(Object source,Class<V> clazz){
        V result = null;
        //创建目标对象
        try {
            result = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source,result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
        //实现属性copy

        //返回结果
    }
    public static <V> List<V> copyBeanList(List<? extends Object> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

}
