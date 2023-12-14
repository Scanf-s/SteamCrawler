package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.ArrayList;
import java.util.List;

public class SteamCrawler {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:\\Java\\untitled\\src\\main\\resources\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();

        // Steam 상점에서, Special Offer 태그 클릭, 원하는 태그 클릭한 후 브라우저 링크를 여기에 붙여넣고, FirebaseManager에서 gameTag 수정
        driver.get("https://store.steampowered.com/search/?tags=599&supportedlang=english&specials=1&hidef2p=1&ndl=1");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String pageSource = driver.getPageSource();

        Document document = Jsoup.parse(pageSource);


        List<GameInfoContainer> gameInfoContainerList = new ArrayList<>();

        Elements gameElements = document.select(".search_result_row");
        for (Element gameElement : gameElements) {
            GameInfoContainer gameInfo = new GameInfoContainer();

            String gameTitle = gameElement.select(".title").text();
            String gameLink = gameElement.select(".search_result_row").attr("href");
            String originalPrice = gameElement.select(".discount_original_price").text();
            String discountedPrice = gameElement.select(".discount_final_price").text();
            String discountRate = gameElement.select(".discount_pct").text();
            String gameImage = gameElement.select(".search_capsule img").attr("src");

            gameInfo.setTitle(gameTitle);
            gameInfo.setGameLink(gameLink);
            gameInfo.setImg(gameImage);
            gameInfo.setOriginalPrice(originalPrice);
            gameInfo.setDiscountPrice(discountedPrice);
            gameInfo.setDiscountRate(discountRate);

            System.out.println("Title: " + gameTitle);
            System.out.println("Link: " + gameLink);
            System.out.println("Original Price: " + originalPrice);
            System.out.println("Discounted Price: " + discountedPrice);
            System.out.println("Discount Rate: " + discountRate);
            System.out.println("Game Image: " + gameImage);
            System.out.println("------------------------------");

            gameInfoContainerList.add(gameInfo);
        }

        FirebaseManager.saveGamesToFirestore(gameInfoContainerList);

        driver.quit();
    }
}
