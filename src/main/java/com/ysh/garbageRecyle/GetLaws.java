package com.ysh.garbageRecyle;

import com.ysh.garbageRecyle.entity.GarbageLawEntity;
import com.ysh.garbageRecyle.service.GarbageLawService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetLaws {

    //静态内部类存储地名和地名页面链接
    static class LocationUrl{
        //地区名字
        private String locationName;
        //地区页面链接
        private String locationPageUrl;
        public String getLocationName() {
            return locationName;
        }

        @Override
        public String toString() {
            return "LocationUrl{" +
                    "locationName='" + locationName + '\'' +
                    ", locationPageUrl='" + locationPageUrl + '\'' +
                    '}';
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getLocationPageUrl() {
            return locationPageUrl;
        }

        public void setLocationPageUrl(String locationPageUrl) {
            this.locationPageUrl = locationPageUrl;
        }
    }
    //静态内部类存储法律实体和页面链接
    static class Laws{
        private String title;
        private String time;
        private String htmlContentFileName;
        private String locationName;
        private String lawsLinkUrl;
        private String lawCode;

        public String getLawCode() {
            return lawCode;
        }

        public void setLawCode(String lawCode) {
            this.lawCode = lawCode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getHtmlContentFileName() {
            return htmlContentFileName;
        }

        public void setHtmlContentFileName(String htmlContentFileName) {
            this.htmlContentFileName = htmlContentFileName;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getLawsLinkUrl() {
            return lawsLinkUrl;
        }

        public void setLawsLinkUrl(String lawsLinkUrl) {
            this.lawsLinkUrl = lawsLinkUrl;
        }

        @Override
        public String toString() {
            return "Laws{" +
                    "title='" + title + '\'' +
                    ", time='" + time + '\'' +
                    ", htmlContentFileName='" + htmlContentFileName + '\'' +
                    ", locationName='" + locationName + '\'' +
                    ", lawsLinkUrl='" + lawsLinkUrl + '\'' +
                    ", lawCode='" + lawCode + '\'' +
                    '}';
        }
    }
    //根据地区链接分析出所有的
    //获取页面地区链接
    public static List<LocationUrl> getLocationUrl(){
        String url="https://lajifenleiapp.com/zhengce";
        Document indexDocument=null;

        List<LocationUrl> locationUrlList=new ArrayList<>();
        try {
            indexDocument= Jsoup.connect(url).get();
            Elements elements=indexDocument.getElementsByClass("col-md-2 col-xs-2");
            System.out.println(elements);

            for (Element element:elements){
                Elements href=element.getElementsByTag("a");
                for(Element h:href){
                    String linkHref=h.attr("href");
                    String location=h.text();
                    LocationUrl locationUrl=new LocationUrl();
                    locationUrl.setLocationName(location);
                    locationUrl.setLocationPageUrl(url+linkHref);
                    locationUrlList.add(locationUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationUrlList;
    }
    //获取法律内容
    public static List<Laws> getLawsByPageUrl(String pageUrl, String locationame,List<String> codeList){
        //新闻资讯首页
        String baseurl="https://lajifenleiapp.com";
        Document indexDocument=null;
        //map(地点，一个地方资讯的页面链接)
        List<Laws> lawsList=new ArrayList<>();
        try {
            indexDocument= Jsoup.connect(pageUrl).get();
            //获取第一个div
            Elements firstElements=indexDocument.getElementsByClass("row list_line");
            for(Element ele:firstElements){
                Laws laws=new Laws();
                //获取标题和链接
                Elements elementsLink=ele.getElementsByClass("col-md-7 col-xs-7");
                System.out.println(elementsLink);

                for (Element element:elementsLink){
                    Elements href=element.getElementsByTag("a");
                    for(Element h:href){
                        String linkHref=h.attr("href");
                        String title=h.text();

                        laws.setLocationName(locationame);
                        laws.setLawsLinkUrl(baseurl+linkHref);
                        laws.setTitle(title);
                        laws.setLawCode(linkHref);
                    }
                }
                //查询数据库中是否有该条数据
                if(codeList.contains(laws.getLawCode())){
                    continue;
                }
                //获取时间
                Elements elementsTime=ele.getElementsByClass("col-md-3 col-xs-3");
                String time=null;
                for (Element element:elementsTime){
                    time =element.text();
                }
                laws.setTime(time);

                //获取法律内容
                String contentFileName=getLawsDetailByLink(laws.getLawsLinkUrl());
                laws.setHtmlContentFileName(contentFileName);
                //添加进列表
                lawsList.add(laws);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lawsList;
    }
    //根据政策链接把政策内容写成html文件，返回文件名
   public  static String getLawsDetailByLink(String lawsDetailUrl){
        String testUrl=lawsDetailUrl;
        Document document=null;
        String fileContentName=null;
        try {
            document= Jsoup.connect(testUrl).get();
            Elements elements=document.getElementsByClass("article-content");
            //写入文件
            // 设置文件名字
            String basePath=Decode.getBaseUrl();
            String fileName=Decode.getImgFileName()+".html";
            String filePath=basePath+"src/main/resources/static/laws/"+fileName;
            OutputStream out = new FileOutputStream(filePath);
            byte[] b=elements.toString().getBytes("UTF-8");
            out.write(b);
            out.flush();
            out.close();
            System.out.println(elements.toString());
            fileContentName=fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContentName;
    }
    /*
     *获取法律实体列表
     */
    public List<GarbageLawEntity> getLawsList(List<String> codelist){
        List<Laws> allNews=new ArrayList<>();
        List<LocationUrl> locationUrlList=getLocationUrl();
        List<GarbageLawEntity> lawEntityList=new ArrayList<>();
        for(LocationUrl locationUrl:locationUrlList){
            allNews.addAll(getLawsByPageUrl(locationUrl.getLocationPageUrl(),locationUrl.getLocationName(),codelist));
        }

        for (Laws law:allNews){
            GarbageLawEntity garbageLawEntity=new GarbageLawEntity();
            garbageLawEntity.setLawFileName(law.getHtmlContentFileName());
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            String s = law.getTime();
            Date date=null;
            try {
                date = formatter.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            garbageLawEntity.setLawPubDate(date);
            garbageLawEntity.setLawTitle(law.getTitle());
            garbageLawEntity.setLawLocation(law.getLocationName());
            garbageLawEntity.setLawFileName(law.getHtmlContentFileName());
            garbageLawEntity.setLawCode(law.getLawCode());
            lawEntityList.add(garbageLawEntity);
        }
        System.out.println(allNews.size());
        return lawEntityList;
    }
}
