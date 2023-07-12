package org.example.models;


import jakarta.validation.constraints.*;

public class Book {

    private int id;
    private int personId;

    @NotEmpty(message = "Название пустое")
    @Pattern(regexp = "[А-Я].+", message = "Неверный формат")
    private String title;

    @NotEmpty(message = "Автор не указан")
    @Pattern(regexp = "[А-Я]\\.[А-Я]\\. [А-Я][а-я]+", message = "Введите автора в следующем формате: \"И.О. Фамилия\"")
    private String author;

    @NotNull(message = "Введите год")
    @Max(value = 2023, message = "Неверная дата")
    @Min(value = 1, message = "Неверная дата")
    private int year;

    public Book(int id, int personId, String title, String author, int year) {
        this.id = id;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
