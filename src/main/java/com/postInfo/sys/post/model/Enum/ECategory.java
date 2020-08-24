package com.postInfo.sys.post.model.Enum;

import java.util.HashMap;
import java.util.Map;

public enum ECategory {
    OTHERS(0, "Other"),
    DEVELOPMENT(1, "Development"),
    TECHNOLOGY(2, "Technology"),
    CPP(3, "Cpp"),
    SCI_FI(4, "Sci-Fi"),
    JAVA(5, "Java"),
    PYTHON(6, "Python");

    private final Integer value;
    private final String code;

    private static final Map<Integer, ECategory> intToEnumMap = new HashMap<>();
    static {
        for (final ECategory type : ECategory.values()) {
            intToEnumMap.put(type.value, type);
        }
    }

    public static ECategory fromInt(final Integer ruleTypeValue) {
        final ECategory type = intToEnumMap.get(ruleTypeValue);
        return type;
    }

    private ECategory(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    }
