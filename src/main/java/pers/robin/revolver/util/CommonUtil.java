package pers.robin.revolver.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
            String value = "";
            if (values != null && (values.length > 1 || !StringUtils.isNotBlank(values[0]))) {
                for (String val : values) {
                    if (val != null && "".equals(val)) {
                        value += val + ",";
                    }
                }
                if (value != null && "".equals(value)) {
                    value = value.substring(0, value.length() - 1);
                }
                if (key.equals("keywords")) {
                    value = value.replace("_", "\\_").replace("%", "\\%");
                }
                map.put(key, value);
            }
        }
        return map;
    }
}
