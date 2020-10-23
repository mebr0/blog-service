package com.mebr0.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String text;

    public static Blog of(String title, String text) {
        Blog blog = new Blog();

        blog.setTitle(title);
        blog.setText(text);

        return blog;
    }

    public void doIt() {
        title = "qwe";
    }
}
