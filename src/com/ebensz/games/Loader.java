package com.ebensz.games;

import com.ebensz.games.scene_providers.Main;
import ice.engine.Game;
import ice.engine.SceneProvider;

public class Loader extends Game {

    @Override
    protected Class<? extends SceneProvider> getEntry() {
        return Main.class;
    }

}