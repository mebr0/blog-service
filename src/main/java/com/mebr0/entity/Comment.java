package com.mebr0.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Schema(name = "Comment", description = "Comment with text")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Unique id of comment", example = "1")
    private Long id;

    @NonNull
    @NotNull(message = "Text of comment cannot be null")
    @NotBlank(message = "Text of comment cannot be blank")
    @Schema(name = "text", description = "Text of comment", example = "default text")
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(name = "blog", description = "Blog which comment is attached", hidden = true)
    private Blog blog;
}
