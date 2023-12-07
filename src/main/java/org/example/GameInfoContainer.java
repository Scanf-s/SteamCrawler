package org.example;

public class GameInfoContainer {
    String title;
    String gameLink;
    String img;
    String originalPrice;
    String discountPrice;
    String discountRate;
    public GameInfoContainer() {
    }
    public GameInfoContainer(String title, String gameLink, String img, String originalPrice, String discountPrice, String discountRate) {
        this.title = title;
        this.gameLink = gameLink;
        this.img = img;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.discountRate = discountRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGameLink() {
        return gameLink;
    }

    public void setGameLink(String gameLink) {
        this.gameLink = gameLink;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }
}
