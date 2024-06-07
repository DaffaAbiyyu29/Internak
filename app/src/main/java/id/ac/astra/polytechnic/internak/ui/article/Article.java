package id.ac.astra.polytechnic.internak.ui.article;

public class Article {
    private String title;
    private String date;

    public Article() {
    }

    public Article(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
