package com.ran.ams.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Riyan Amanda
 * {@code @linkedin} <a href="https://id.linkedin.com/in/riyan-amanda/in">...</a>
 * @since 09/12/2024
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;
}
