package gudu.csy.utils;

import java.util.UUID;

/**
 * 生成随机字符串
 */
public class UUIDUtils {
    /**
     *
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
