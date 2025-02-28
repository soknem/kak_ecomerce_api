package com.ecomerce.api.domain;

import com.ecomerce.api.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100)
    private String uuid;

    @Column(unique = true)
    private String username;

    private String password;

    private String rawPassword;

    @Column(unique = true)
    private String email;

    private String phone;

    private String avatar;

    private String address;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;

    private Boolean isDeleted;
    private Boolean isChangePassword;

    @ManyToOne()
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ShippingAddress> shippingAddresses;
}
