package com.easy359.engine.menu;

import com.easy359.engine.game.GameState;
import com.easy359.engine.gfx.DisplayImage;

import java.util.ArrayList;
import java.util.List;

public class Menu implements GameState {

    private List<MenuComponent> components;

    public Menu() {
        components = new ArrayList<MenuComponent>();
    }

    public void update(double delta) {
        for (MenuComponent component : components) {
            component.update(delta);
        }
    }

    public void addMenuComponent(MenuComponent component) {
        components.add(component);
    }

    public void addMenuComponent(List<MenuComponent> components) {
        for (MenuComponent interactable : components) {
            addMenuComponent(interactable);
        }
    }

    public List<DisplayImage> getDisplayImages() {
        List<DisplayImage> imgs = new ArrayList<DisplayImage>();
        for (MenuComponent component : components) {
            imgs.add(component.getDisplayImage());
        }
        return imgs;
    }

}
