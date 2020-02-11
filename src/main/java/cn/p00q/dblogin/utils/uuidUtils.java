package cn.p00q.dblogin.utils;


import java.util.UUID;

/**
 * @author danbai
 * @date 2020-02-04 17:49
 */
public class uuidUtils {
    /**
     * 获取uuid
     *
     * @param num 长度
     * @return uuid
     */
    public static String[] getUUID(int num) {

        if (num <= 0) {
            return null;
        }
        String[] uuidArr = new String[num];

        for (int i = 0; i < uuidArr.length; i++) {
            uuidArr[i] = getUUID32();
        }

        return uuidArr;
    }

    /**
     * 得到32位的uuid
     *
     * @return 32位的uuid
     */
    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
