package com.mebr0.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Schema(name = "Blog", description = "Blog with title and text")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Unique id of blog", example = "1")
    private Long id;

    @NonNull
    @NotNull(message = "Title of blog cannot be null")
    @Size(min = 3, max = 25, message = "Title of blog must be between 3 and 25")
    @Schema(name = "title", description = "Title of blog", example = "default title")
    private String title;

    @NonNull
    @NotNull(message = "Text of blog cannot be null")
    @NotBlank(message = "Text of blog cannot be blank")
    @Schema(name = "text", description = "Text of blog", example = "default text")
    private String text;

    @JsonManagedReference
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(name = "comments", description = "Comments attached to blog", type = SchemaType.ARRAY, implementation = Comment.class)
    private List<Comment> comments = new ArrayList<>();
}
