package com.mebr0.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Schema(name = "Blog", description = "Blog with title and text")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Schema(name = "id", description = "Unique id of blog", example = "1")
    private Long id;

    @NonNull
    @Schema(name = "title", description = "Title of blog", example = "default title")
    private String title;

    @NonNull
    @Schema(name = "text", description = "Text of blog", example = "default text")
    private String text;

    @JsonManagedReference
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(name = "comments", description = "Comments attached to blog", type = SchemaType.ARRAY, implementation = Comment.class)
    private List<Comment> comments = new ArrayList<>();

    public static Blog of(String title, String text) {
        Blog blog = new Blog();

        blog.setTitle(title);
        blog.setText(text);

        return blog;
    }
}
