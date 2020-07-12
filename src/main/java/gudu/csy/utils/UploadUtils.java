package gudu.csy.utils;

public class UploadUtils {
    //根据fileItem得到的文件名得到只包含名称和文件格式的文件名
    public static String getFilename(String name) {
        int index = name.indexOf("//");
        if (index == -1) {
            return name;
        }
        return name.substring(index + 1);
    }
 
    //根据文件名的hashcode生成2级目录
    public static String getRandomDir(String filename) {
        int hashcode = filename.hashCode();
        //1级目录
        int d1 = hashcode & 0xf;
        //2级目录
        int d2 = (hashcode >> 4) & 0xf;
        return "/" + d1 + "/" + d2;
    }
 
    //返回随机的uuid+文件格式的文件名
    public static String getUuidName(String filename) {
        String uuid = UUIDUtils.getUUID();
        int index = filename.indexOf(".");
        if (index == -1) {
            return uuid;
        }
        return uuid + filename.substring(index);
    }
}
