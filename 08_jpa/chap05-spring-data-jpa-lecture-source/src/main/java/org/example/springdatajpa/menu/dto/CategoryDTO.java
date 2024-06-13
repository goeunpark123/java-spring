package org.example.springdatajpa.menu.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString   // 양방향 관계 순환 참조?
public class CategoryDTO {

    private int categoryCode;

    private String categoryName;

    private Integer refCategoryCode;
}
