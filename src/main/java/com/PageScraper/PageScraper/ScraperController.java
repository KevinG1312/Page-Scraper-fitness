package com.PageScraper.PageScraper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ScraperController {

    @GetMapping("/scrape")
    public String scrapePage() {
        String url = "https://www.fitness-superstore.co.uk/body-power-tri-grip-cast-iron-olympic-2-inch-discs-15kg-x2.html";
        try {
            Connection.Response response = Jsoup.connect(url)
                    .execute();

            Document doc = response.parse();
            String pageContent = doc.html(); // Retrieve HTML content
            // Convert HTML content to lowercase for case-insensitive search
            pageContent = pageContent.toLowerCase();
            // Check if the string "Stock coming soon!" is present
            if (pageContent.contains("stock coming soon!")) {
                return "Out of Stock";
            } else {
                return "In Stock!!!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while scraping the page: " + e.getMessage();
        }
    }
}
