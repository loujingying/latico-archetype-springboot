package com.latico.archetype.springboot.common.envm;

import java.util.HashMap;
import java.util.Map;

/**
 * <PRE>
 * 枚举模板示例
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2019-06-06 14:15:31
 * @version: 1.0
 */
public enum DemoEnum {

    /**
     * 枚举实例1
     */
    enum1(1, "name1", "value1"),

    /**
     * 枚举实例2
     */
    enum2(2, "enum2", "value2"),
    ;

    /**
     * nameEnumMap 名字和枚举对象的映射
     */
    private final static Map<String, DemoEnum> NAME_ENUM_MAP = new HashMap<String, DemoEnum>();

    /**
     * idEnumMap ID值和对象的映射
     */
    private final static Map<Integer, DemoEnum> ID_ENUM_MAP = new HashMap<Integer, DemoEnum>();
    /**
     * 值和枚举对象的映射
     */
    private final static Map<String, DemoEnum> VALUE_ENUM_MAP = new HashMap<String, DemoEnum>();

    static {
        DemoEnum[] values = values();
        for (DemoEnum value : values) {
            // 统一转换成小写
            NAME_ENUM_MAP.put(value.getName().toLowerCase(), value);
            ID_ENUM_MAP.put(value.id, value);
            VALUE_ENUM_MAP.put(value.value, value);
        }
    }

    /**
     * 枚举存储的值
     */
    private int id;

    /**
     * 枚举名称，必须有，并且统一转换成小写
     */
    private String name;

    /**
     * 枚举指定的值
     */
    private String value;


    /**
     * 构造函数
     * 枚举参数初始化
     *
     * @param id
     * @param name
     */
    private DemoEnum(int id, String name, String value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    /**
     * 获取当前枚举对象的枚举名称
     *
     * @return 名字
     */
    public String getName() {
        return name;
    }

    /**
     * @return 返回一个值
     */
    public String getValue() {
        return value;
    }

    /**
     * 获取当前对象的枚举ID
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * 通过枚举的名称获取枚举对象
     *
     * @param name
     * @return
     */
    public static DemoEnum getEnumByName(String name) {
        if (name != null) {
            //统一转换成小写
            return NAME_ENUM_MAP.get(name.toLowerCase());
        } else {
            return null;
        }
    }

    /**
     * 通过枚举的ID获取枚举对象
     *
     * @param id
     * @return
     */
    public static DemoEnum getEnumById(int id) {
        return ID_ENUM_MAP.get(id);
    }

    /**
     * 通过值查找枚举
     *
     * @param value
     * @return
     */
    public static DemoEnum getEnumByValue(String value) {
        if (value != null) {
            //统一转换成小写
            return VALUE_ENUM_MAP.get(value);
        } else {
            return null;
        }
    }
}
