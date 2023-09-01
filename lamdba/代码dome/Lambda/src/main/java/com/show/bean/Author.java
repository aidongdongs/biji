package com.show.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.Comparator;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode //
public class Author implements Comparable<Author> {
    private Long id;

    private String name;

    private Integer age;

    private String intro;

    private List<Book> books;

    @Override
    public int compareTo(Author o) {
        return this.getAge()-o.getAge();
    }
}
