package com.pse.cominfo.util;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.common.collect.Maps;

public class SearchScraper {

    private static final String URL = "http://edge.pse.com.ph/companyDirectory/search.ax";

    public static void main(String[] args) throws IOException {
        Map<String, String> map = Maps.newHashMap();
        for (int i = 1; i < 6; i++) {
            Document doc = Jsoup.connect(URL)
                    //pageNo=2&companyId=&keyword=&sortType=&dateSortType=DESC&cmpySortType=ASC&symbolSortType=ASC&sector=ALL&subsector=ALL
                    .data("pageNo", i + "")
                    .data("dateSortType", "DESC")
                    .data("cmpySortType", "ASC")
                    .data("symbolSortType", "ASC")
                    .data("sector", "ALL")
                    .data("subsector", "ALL")
                    .post();
            Elements elements = doc.select("tr").select("td:nth-child(2)");
            elements.stream().forEach(e -> {
                map.put(e.text(), extractId(e.select("a").attr("onclick")));
            });
        }
        map.forEach((k, v) -> {
            System.out.println(k + "," + v);
        }); 
    }

    private static String extractId(String onclick) {
        if (null == onclick || onclick.length() == 0 || onclick.indexOf('\'') == -1) {
            return "";
        }
        return onclick.substring(onclick.indexOf('\'') + 1, onclick.indexOf('\'', onclick.indexOf('\'') + 1));
    }
}
