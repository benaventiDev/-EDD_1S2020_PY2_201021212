package edd.tree;
import org.json.simple.JSONObject;


public class Book {
    private String ISBN;
    private String title;
    private String author;
    private String editorial;
    private String year;
    private String edition;
    private String category;
    private String language;
    private String carnet;

    public Book(String ISBN){
        this.setISBN(ISBN);
    }

    public Book(JSONObject obj, String carnet){

        this.ISBN = (long) obj.get("ISBN") + "";
        this.title = (String) obj.get("Titulo");
        this.author = (String) obj.get("Autor");
        this.editorial = (String) obj.get("Editorial");
        this.year = (long) obj.get("Año") + "";
        this.edition = (long) obj.get("Edicion") + "";
        this.category = (String) obj.get("Categoria");
        this.language = (String) obj.get("Idioma");
        this.carnet = carnet;
    }

    @Override
    public String toString() {
        return "ISBN: " + ISBN + ". Title: " + title + ". Category: " + category;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
}
