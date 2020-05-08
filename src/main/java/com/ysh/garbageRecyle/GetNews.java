package com.ysh.garbageRecyle;

import com.ysh.garbageRecyle.entity.NewsEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetNews {
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
    //静态内部类存储新闻实体和页面链接
    static class News{
        private String title;


        @Override
        public String toString() {
            return "News{" +
                    "title='" + title + '\'' +
                    ", time='" + time + '\'' +
                    ", htmlContent='" + htmlContent + '\'' +
                    ", locationName='" + locationName + '\'' +
                    ", newsLinkUrl='" + newsLinkUrl + '\'' +
                    ", newsCode='" + newsCode + '\'' +
                    '}';
        }

        private String time;
        private String htmlContent;
        private String locationName;
        private String newsLinkUrl;
        private String newsCode;

        public String getNewsCode() {
            return newsCode;
        }

        public void setNewsCode(String newsCode) {
            this.newsCode = newsCode;
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

        public String getHtmlContent() {
            return htmlContent;
        }

        public void setHtmlContent(String htmlContent) {
            this.htmlContent = htmlContent;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getNewsLinkUrl() {
            return newsLinkUrl;
        }

        public void setNewsLinkUrl(String newsLinkUrl) {
            this.newsLinkUrl = newsLinkUrl;
        }
    }
    //根据地区链接分析出所有的
    //获取页面地区链接
    public static List<LocationUrl> getLocationUrl(){
        //新闻资讯首页
        String url="https://lajifenleiapp.com/zixun";
        Document indexDocument=null;
        //map(地点，一个地方资讯的页面链接)
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
    //获取新闻内容
    public static List<News> getNewsByPageUrl(String pageUrl,String locationame,List<String> codelist){
        //新闻资讯首页
        String baseurl="https://lajifenleiapp.com";
        Document indexDocument=null;
        //map(地点，一个地方资讯的页面链接)
        List<News> newsList=new ArrayList<>();
        try {
            indexDocument= Jsoup.connect(pageUrl).get();
            //获取第一个div
            Elements firstElements=indexDocument.getElementsByClass("row list_line");
            for(Element ele:firstElements){
                News news=new News();
                //获取标题和链接
                Elements elementsLink=ele.getElementsByClass("col-md-7 col-xs-7");
                System.out.println(elementsLink);

                for (Element element:elementsLink){
                    Elements href=element.getElementsByTag("a");
                    for(Element h:href){
                        String linkHref=h.attr("href");
                        String title=h.text();

                        news.setLocationName(locationame);
                        news.setNewsLinkUrl(baseurl+linkHref);
                        news.setTitle(title);
                        news.setNewsCode(linkHref);
                    }
                }
                if(codelist.contains(news.getNewsCode())){
                    continue;
                }
                //获取时间
                Elements elementsTime=ele.getElementsByClass("col-md-3 col-xs-3");
                String time=null;
                for (Element element:elementsTime){
                    time =element.text();
                }
                news.setTime(time);

                //获取新闻内容
                String content=getNewsDetailByLink(news.getNewsLinkUrl());
                news.setHtmlContent(content);
                //添加进列表
                newsList.add(news);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newsList);
        return newsList;
    }
    //根据新闻链接获取新闻详情
    public  static String getNewsDetailByLink(String newsDetailUrl){
       String testUrl=newsDetailUrl;
        Document document=null;
        String content=null;
        try {
            document=Jsoup.connect(testUrl).get();
            Elements elements=document.getElementsByClass("article-content");
            content=elements.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    //获取新闻实体列表
    public List<NewsEntity> getNewsList(List<String> codelist){
        List<News> allNews=new ArrayList<>();
        List<LocationUrl> locationUrlList=getLocationUrl();
        List<NewsEntity> newsEntityList=new ArrayList<>();
        for(LocationUrl locationUrl:locationUrlList){
            allNews.addAll(getNewsByPageUrl(locationUrl.getLocationPageUrl(),locationUrl.getLocationName(),codelist));
        }

        for (News news:allNews){
            NewsEntity newsEntity=new NewsEntity();
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:MM:SS");
            String s =news.getTime();
            Date date=null;
            try {
                date=formatter.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            newsEntity.setNewsPubDate(date);
            newsEntity.setNewsTitle(news.getTitle());
            newsEntity.setNewsContentHtml(news.getHtmlContent());
            newsEntity.setCity(news.getLocationName());
            newsEntity.setNewsCode(news.getNewsCode());
            newsEntityList.add(newsEntity);
        }
        return newsEntityList;
    }
}
