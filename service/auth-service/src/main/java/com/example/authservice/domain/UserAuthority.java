package com.example.authservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users_authorities")
public class UserAuthority {

    @EmbeddedId
    private UserAuthorityPrimaryKey userAuthorityPrimaryKey;
}
