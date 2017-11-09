package com.boot.util.htmlunit;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

/**
 * htmlunit爬取汽车之家汽车品牌和图标
 * Created by zhouxiaoxiao on 17/6/18.
 */
public class htmlunit {

    public static void main(String[] args) throws Exception {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);//关闭css
        webClient.getOptions().setJavaScriptEnabled(false);//关闭js
        HtmlPage page = webClient.getPage("http://www.autohome.com.cn/car/");
        // 获取当前页的所有class="i"元素。返回 DOM节点列表
        DomNodeList<DomNode> iList = page.querySelectorAll(".rank-list");
        // 遍历
        for (int i = 0; i < iList.getLength(); i++) {
            // 选择 p 元素
            if (i == 24) break;
            DomNodeList<DomNode> dlList = iList.get(i).querySelectorAll("dl");
            for (DomNode o : dlList) {
                DomNode dt = o.querySelector("dt");
                DomNode a = dt.querySelector("a");
                HtmlImage img = a.querySelector("img");
                String original = img.getAttribute("data-original");
                DomNode div = dt.querySelector("div");
                HtmlAnchor aa = (HtmlAnchor) div.querySelector("a");
                // 解码后在组装
                String car = new String(aa.getTextContent().getBytes(), "GBK").replace('?', '-');
                System.out.println(original);
                System.out.println(new String(car.getBytes("UTF-8"),"UTF-8"));
                DomNode dd = o.querySelector("dd");
                DomNodeList<DomNode> ulList = dd.querySelectorAll("ul");
                for (DomNode ul : ulList) {
                    DomNodeList<DomNode> liList = ul.querySelectorAll("li");
                    for (DomNode li : liList) {
                        DomNode h4 = li.querySelector("h4");
                        if (h4 == null) continue;
                        HtmlAnchor aaa = h4.querySelector("a");
                        String car_name = new String(aaa.getTextContent().getBytes(), "GBK").replace('?', '-');
                        System.out.println(new String(car_name.getBytes("UTF-8"),"UTF-8"));
                    }
                }
            }
        }
        webClient.closeAllWindows();
    }
}