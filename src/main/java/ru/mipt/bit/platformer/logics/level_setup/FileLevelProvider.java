package ru.mipt.bit.platformer.logics.level_setup;

import ru.mipt.bit.platformer.logics.models.*;
import ru.mipt.bit.platformer.util.Vector2D;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.List;


public class FileLevelProvider implements LevelProvider {
    private Level level;
    private String levelFilePath;
    private final Character treeChar = 'T';
    private final Character playerChar = 'X';


    public FileLevelProvider(String filePath){
        levelFilePath = filePath;
    }

    @Override
    public Level getLevel(){
        return configureFromFile(levelFilePath);
    }

    public Level configureFromFile(String filePath){
        ArrayList<String> levelLines = readLevel(filePath);

        ParseLevelLinesResult result = parseLevelLines(levelLines);
        Tank playerTank = result.getPlayerTank();
        //ArrayList<GameObject> gameObjects = result.getGameObjects();
        ArrayList<Tank> tanks = result.getTanks();
        ArrayList<Tree> trees = result.getTrees();

        Vector2D leftCorner = new Vector2D(0, 0);
        Vector2D rightCorner = getRightCorner(levelLines);

        level = new Level(leftCorner, rightCorner, tanks, trees, playerTank);

        return level;
    }

    private Vector2D getRightCorner(ArrayList<String> levelLines) {
        return new Vector2D(levelLines.getFirst().length() - 1, levelLines.size() - 1);
    }


    private ParseLevelLinesResult parseLevelLines(ArrayList<String> levelLines) {
        Vector2D rightCorner = getRightCorner(levelLines);

        //ArrayList<GameObject> gameObjects = new ArrayList<>();
        ArrayList<Tank> tanks = new ArrayList<>();
        ArrayList<Tree> trees = new ArrayList<>();
        Tank playerTank = null;
        for (int i = 0; i < levelLines.size(); i++) {
            for (int j = 0; j < levelLines.get(i).length(); j++) {
                Character c = levelLines.get(i).charAt(j);
                Vector2D coord = new Vector2D(j, (int)rightCorner.y() - i);
                if(c == treeChar){
                    trees.add(new Tree(coord));
                } else if (c == playerChar) {
                    playerTank = new Tank(coord, Direction.UP);
                    tanks.add(playerTank);
                }
            }

        }
        return new ParseLevelLinesResult(playerTank, tanks, trees);
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


    private class ParseLevelLinesResult{
        private final Tank playerTank;
        //private final ArrayList<GameObject> gameObjects;
        private final ArrayList<Tank> tanks;
        private final ArrayList<Tree> trees;

        public ParseLevelLinesResult(Tank playerTank, ArrayList<Tank> tanks, ArrayList<Tree> trees) {
            this.playerTank = playerTank;
            this.tanks = tanks;
            this.trees = trees;
        }

        public Tank getPlayerTank() {
            return playerTank;
        }

        public ArrayList<Tree> getTrees(){
            return trees;
        }

        public ArrayList<Tank> getTanks(){
            return tanks;
        }
    }
}
