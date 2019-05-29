package com.example.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserAuthorityPrimaryKey implements Serializable {

    protected Long user_id;
    protected Long authority_id;
}
