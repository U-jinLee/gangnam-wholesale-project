package com.gangnam.wholesale.domain.product;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "categories")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories = new ArrayList<>();

    @Builder
    public Category(String name) {
        this.name = name;
    }

    public void addSubCategory(Category category) {
        this.subCategories.add(category);
        category.setParentCategory(this);
    }

    public void setParentCategory(Category category) {
        this.parentCategory = category;
        if (!category.getSubCategories().contains(this)) category.getSubCategories().add(this);
    }

}