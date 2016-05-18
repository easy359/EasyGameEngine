package com.easy359.engine.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easy359.engine.gfx.DisplayImage;

public class GameStateManager {

    private Map<String, GameState> gameStates;
    private String activeMenu;

    public GameStateManager() {
	gameStates = new HashMap<String, GameState>();
    }

    public void update(double delta) {
	gameStates.get(activeMenu).update(delta);
    }

    public void addGameState(String key, GameState gameState) {
	gameStates.put(key, gameState);
    }

    public void removeGameState(String key) {
	gameStates.remove(key);
    }

    public void setActiveGameState(String key) {
	activeMenu = key;
    }

    public boolean hasActiveGameState() {
	return activeMenu != null;
    }

    public List<DisplayImage> getDisplayImages() {
	return gameStates.get(activeMenu).getDisplayImages();
    }

}
