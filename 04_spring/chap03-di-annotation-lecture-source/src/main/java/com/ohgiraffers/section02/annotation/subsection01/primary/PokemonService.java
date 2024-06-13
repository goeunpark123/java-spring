package com.ohgiraffers.section02.annotation.subsection01.primary;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pokemonServicePrimary")
public class PokemonService {
    private Pokemon pokemon;

    /*설명.
       @Autowired는 생략하면 매개변수 있는 생성자에 자동으로 작성(자동으로 생성자 주입)
       현재와 같이 Pokemon 타입의 빈이 2개 이상일 경우 @Primary 어노테이션을 통해 하나의 빈만 주입 가능*/

    @Autowired          // 자동으로 된다?
    public PokemonService(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
