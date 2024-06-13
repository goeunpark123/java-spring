package com.ohgiraffers.section02.annotation.subsection02.qualifier;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PokemonService {

    /*설명.
    *  @Qualifier를 통해 원하는 bean 이름(id)으로 하나의 빈 주입 가능(@Primary보다 높은 우선 순위)*/

//    @Autowired
//    @Qualifier("squirtle")
    private Pokemon pokemon;

    public PokemonService(@Qualifier("squirtle") Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
