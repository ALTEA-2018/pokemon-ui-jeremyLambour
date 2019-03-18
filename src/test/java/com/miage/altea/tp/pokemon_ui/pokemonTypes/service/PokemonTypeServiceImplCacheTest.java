package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;

@SpringBootTest
public class PokemonTypeServiceImplCacheTest {
	@Autowired
    PokemonTypeService pokemonTypeService;

    @Mock
    RestTemplate restTemplate;

    @Value("${pokemonType.service.url}/pokemon-types/{id}")
    String expectedUrl;

    @Autowired
    CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pokemonTypeService.setRestTemplate(restTemplate); 

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("Pikachu");
        when(restTemplate.getForObject(expectedUrl, PokemonType.class, 25)).thenReturn(pikachu);
    }

    @Test
    void getPokemonType_shouldUseCache() {
        pokemonTypeService.getPokemonById(25);

        // rest template should have been called once
        verify(restTemplate).getForObject(expectedUrl, PokemonType.class, 25);

        pokemonTypeService.getPokemonById(25);

        // rest template should not be called anymore because result is in cache ! 
        verifyNoMoreInteractions(restTemplate);

        // one result should be in cache !
        var cachedValue = cacheManager.getCache("pokemon-types").get(25).get();
        assertNotNull(cachedValue);
        assertEquals(PokemonType.class, cachedValue.getClass());
        assertEquals("Pikachu", ((PokemonType)cachedValue).getName());
    }
}
