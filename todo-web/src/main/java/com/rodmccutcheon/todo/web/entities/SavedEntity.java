package com.rodmccutcheon.todo.web.entities;

public class SavedEntity<T> {
    private Long id;
    private String url;
    private T value;

    public SavedEntity(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public SavedEntity(Long id, String url, T value) {
        this.id = id;
        this.url = url;
        this.value = value;
    }

    protected SavedEntity() {}

    public Long getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public T getValue() { return value; }
}
