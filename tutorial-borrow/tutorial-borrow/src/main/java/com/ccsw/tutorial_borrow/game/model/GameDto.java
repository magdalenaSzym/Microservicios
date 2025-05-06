package com.ccsw.tutorial_borrow.game.model;

import com.ccsw.tutorial_borrow.author.model.AuthorDto;
import com.ccsw.tutorial_borrow.category.model.CategoryDto;

public class GameDto {

    private Long id;
    private String title;
    private String age;
    private CategoryDto category;
    private AuthorDto author;

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title new value of {@link #getTitle}.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age new value of {@link #getAge}.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return category
     */
    public CategoryDto getCategory() {

        return this.category;
    }

    /**
     * @param category new value of {@link #getCategory}.
     */
    public void setCategory(CategoryDto category) {

        this.category = category;
    }

    /**
     * @return author
     */
    public AuthorDto getAuthor() {

        return this.author;
    }

    /**
     * @param author new value of {@link #getAuthor}.
     */
    public void setAuthor(AuthorDto author) {

        this.author = author;
    }

}
