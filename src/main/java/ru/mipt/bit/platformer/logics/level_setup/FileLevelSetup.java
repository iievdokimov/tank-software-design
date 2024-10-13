package ru.mipt.bit.platformer.logics.level_setup;

import ru.mipt.bit.platformer.logics.models.*;
import ru.mipt.bit.platformer.util.Vector2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;


public class FileLevelSetup implements LevelSetup {
    private Level level;
    private final Character treeChar = 'T';
    private final Character playerChar = 'X';
    private Tank playerTank = null;

    public FileLevelSetup(String filePath){
        level = configureFromFile(filePath);
    }

    public Level getLevel(){
        return level;
    }

    public Level configureFromFile(String filePath){
        ArrayList<String> levelLines = readLevel(filePath);


        ArrayList<GameObject> gameObjects = new ArrayList<>();
        //Tank playerTank = null;
        parseLevelLines(gameObjects, levelLines);
        Vector2D leftCorner = new Vector2D(0, 0);
        Vector2D rightCorner = getRightCorner(levelLines);

        level = new Level(leftCorner, rightCorner, gameObjects, playerTank);

        return level;
    }

    private Vector2D getRightCorner(ArrayList<String> levelLines) {
        return new Vector2D(levelLines.getFirst().length(), levelLines.size());
    }

    private void parseLevelLines(ArrayList<GameObject> gameObjects, ArrayList<String> levelLines) {
        Vector2D rightCorner = getRightCorner(levelLines);
        for (int i = 0; i < levelLines.size(); i++) {
            for (int j = 0; j < levelLines.get(i).length(); j++) {
                Character c = levelLines.get(i).charAt(j);
                Vector2D coord = new Vector2D(j, (int)rightCorner.y() - i);
                if(c == treeChar){
                    gameObjects.add(new Tree(coord));
                } else if (c == playerChar) {
                    playerTank = new Tank(coord, Direction.UP);
                    gameObjects.add(playerTank);
                }
            }

        }
    }

    private ArrayList<String> readLevel(String filePath) {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            ArrayList<String> result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            return result;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
