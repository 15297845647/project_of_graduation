package com.ysh.garbageRecyle;

import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Decode {
    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr)
    {  //对字节数组字符串进行Base64解码并生成图片
        if(imgStr == null){return false;} //图像数据为空
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "";//新生成的图片
            //根据UUID生成文件名字
            String fileName=getImgFileName()+".jpg";
            //获得项目路径
            String projectPath=getBaseUrl();
            //拼接生成绝对路径
             imgFilePath=projectPath+"src/main/resources/static/images/laws/"+fileName;
             System.out.println(imgFilePath);
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    //获取项目的路径
    public static  String  getBaseUrl() {

        String path = null;
        try {
            String serverpath= ResourceUtils.getURL("").getPath().replace("%20"," ");
            path=serverpath.substring(1);//从路径字符串中取出工程路径
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path;
    }
    //生成随机的UUID作为文件名字
    public static String getImgFileName(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }


}
