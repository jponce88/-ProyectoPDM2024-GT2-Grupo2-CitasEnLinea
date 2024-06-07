package sv.edu.ues.fia.eisi.proyectoinnovador.activities;

public class CardItem {
    private String title;
    private String imageUrl;

    public CardItem(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
