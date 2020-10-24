package com.mebr0.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    public static Comment of(String text) {
        Comment comment = new Comment();

        comment.setText(text);

        return comment;
    }
}
