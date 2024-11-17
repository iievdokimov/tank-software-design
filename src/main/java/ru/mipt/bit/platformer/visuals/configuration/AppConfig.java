package ru.mipt.bit.platformer.visuals.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mipt.bit.platformer.logics.level_setup.LevelProvider;
import ru.mipt.bit.platformer.logics.level_setup.RandomLevelProvider;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.util.Vector2D;
import ru.mipt.bit.platformer.visuals.Drawer;
import ru.mipt.bit.platformer.visuals.GdxDrawer;
import ru.mipt.bit.platformer.visuals.HealthBarSettings;

@Configuration
public class AppConfig {


    @Bean
    public Vector2D startVector() {
        return new Vector2D(0, 0); // Начальная точка, если она вам нужна
    }

    @Bean
    public Vector2D endVector() {
        return new Vector2D(7, 7); // Задайте необходимые координаты для endVector
    }

    @Bean
    public Vector2D levelSize(Vector2D startVector, Vector2D endVector) {
        return endVector.sub(startVector).add(new Vector2D(1, 1));
    }

    @Bean
    public LevelProvider levelProvider(Vector2D startVector, Vector2D endVector) {
        return new RandomLevelProvider(
                startVector, endVector,
                0.1f, 4);
    }

    @Bean
    public LevelProvider levelProvider() {
        return new RandomLevelProvider(
                new Vector2D(0, 0),
                new Vector2D(7, 7),
                0.1f, 4
        );
    }

    @Bean
    public HealthBarSettings healthBarSettings(){
        return new HealthBarSettings(true);
    }

    @Bean
    public int squareTileWidth(){
        return 128;
    }

//    @Bean
//    public Drawer drawer(Level level, HealthBarSettings healthBarSettings) {
//        return new GdxDrawer(level, healthBarSettings);
//    }
}
