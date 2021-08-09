package com.example.test.entity;

import com.example.test.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {

    @NotBlank
    @Size(min = 5)
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Enumerated
    private boolean active;

}
