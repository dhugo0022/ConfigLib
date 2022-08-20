package de.exlll.configlib;

import java.util.Objects;

final class Validator {
    private Validator() {}

    static <T> T requireNonNull(T object, String argumentName) {
        String msg = "The " + argumentName + " must not be null.";
        return Objects.requireNonNull(object, msg);
    }

    static <T> T requireNonNullArrayElement(T element, String type, int index) {
        if (element == null) {
            String msg = "The " + type + " element at index " + index + " must not be null.";
            throw new ConfigurationException(msg);
        }
        return element;
    }

    static <T> Class<T> requireConfigurationClass(Class<T> cls) {
        requireNonNull(cls, "type");
        if (!Reflect.isConfigurationClass(cls)) {
            String msg = "Class '" + cls.getSimpleName() + "' must be a configuration.";
            throw new ConfigurationException(msg);
        }
        return cls;
    }

    static <T> Class<T> requireRecord(Class<T> cls) {
        requireNonNull(cls, "type");
        if (!cls.isRecord()) {
            String msg = "Class '" + cls.getSimpleName() + "' must be a record.";
            throw new ConfigurationException(msg);
        }
        return cls;
    }

    static <T> Class<T> requireConfigurationType(Class<T> type) {
        requireNonNull(type, "type");
        if (!Reflect.isConfigurationType(type)) {
            String msg = "Class '" + type.getSimpleName() + "' must be a configuration or record.";
            throw new ConfigurationException(msg);
        }
        return type;
    }

    static void requirePrimitiveOrWrapperNumberType(Class<?> cls) {
        if (!Reflect.isIntegerType(cls) && !Reflect.isFloatingPointType(cls)) {
            String msg = "Class " + cls.getSimpleName() + " is not a byte, short, int, long, " +
                         "float, double, or a wrapper type of one of the primitive number types.";
            throw new IllegalArgumentException(msg);
        }
    }
}