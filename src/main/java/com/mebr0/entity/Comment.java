package com.mebr0.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Schema(name = "Comment", description = "Comment with text")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Schema(name = "id", description = "Unique id of comment", example = "1")
    private Long id;

    @NonNull
    @Schema(name = "text", description = "Text of comment", example = "default text")
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(name = "blog", description = "Blog which comment is attached", hidden = true)
    private Blog blog;

    public static Comment of(String text) {
        Comment comment = new Comment();

        comment.setText(text);

        return comment;
    }
}
