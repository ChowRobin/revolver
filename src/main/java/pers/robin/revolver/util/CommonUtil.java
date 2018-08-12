package pers.robin.revolver.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommonUtil {

    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> map = new HashMap<String, Object>();
        Set<String> keySet = properties.keySet();
        for (String key : keySet) {
            String[] values = properties.get(key);
            String value = values[0];
            map.put(key, value);
        }
        return map;
    }

    public static Map<String, Object> getObjectFieldsAndValue(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        Map<String, Object> map = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = field.getName();
            String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            String fieldType = field.getGenericType().getTypeName();
            Method method = clazz.getMethod("get" + name);
            Object value = method.invoke(object);
            if (fieldType.equals("java.lang.String")) {
                value = (String) value;
            } else if (fieldType.equals("java.lang.Integer")) {
                value = (Integer) value;
            } else if (fieldType.equals("java.lang.Short")) {
                value = (Short) value;
            } else if (fieldType.equals("java.lang.Double")) {
                value = (Double) value;
            } else if (fieldType.equals("java.lang.Boolean")) {
                value = (Boolean) value;
            } else if (fieldType.equals("java.util.Date")) {
                value = (Date) value;
            } else if (fieldType.equals("java.lang.Long")) {
                value = (Long) value;
            } else if (field.equals("java.lang.Float")) {
                value = (Float) value;
            }
            map.put(fieldName, value);
        }
        return map;
    }

    public static void printObjectFieldsAndValue(Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> map = getObjectFieldsAndValue(object);
        System.out.println(object.getClass().getSimpleName());
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

}
