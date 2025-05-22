package com.kidspro;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
}
