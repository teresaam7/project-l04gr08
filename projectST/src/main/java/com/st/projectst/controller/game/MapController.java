package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.menu.GameOver;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.model.menu.Start;
import com.st.projectst.model.menu.Win;
import com.st.projectst.states.GameOverState;
import com.st.projectst.states.PauseState;
import com.st.projectst.states.StartState;
import com.st.projectst.states.WinState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class  MapController extends LevelController {
    private final MariController mariController;
    private final GhostEnemyController ghostController;
    private final BatEnemyController batController;
    private final PlatformController platformController;
    private final CameraController cameraController;
    private final PotionController potionController;
    private int cameraCount;

    public MapController(Map map) {
        super(map);

        this.mariController = new MariController(map);
        this.ghostController = new GhostEnemyController(map);
        this.batController = new BatEnemyController(map);
        this.platformController = new PlatformController(map);
        this.cameraController = new CameraController(map);
        this.potionController = new PotionController(map);
        this.cameraCount = 0;
    }

    public void step(Main main, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        if (action == GUI.ACTION.QUIT) {
            main.setState(new StartState(new Start(0)));
        } else if (action == GUI.ACTION.PAUSE) {
            main.getGui().close();
            LanternaGUI gui = new LanternaGUI(53, 27, 18);
            main.setGui(gui);
            main.setState(new PauseState(new Pause(main.getState())));
        } else if (getModel().isAtDoor(getModel().getMari().getPosition()) && getModel().getMari().getWithKey()) {
            main.getGui().close();
            LanternaGUI gui = new LanternaGUI(53, 27, 18);
            main.setGui(gui);
            main.setState(new WinState(new Win(getModel().getCurrentLevel() + 1)));
        } else if (getModel().getMari().getRemainingLives() == 0) {
            main.getGui().close();
            LanternaGUI gui = new LanternaGUI(74, 40, 13);
            main.setGui(gui);
            main.setState(new GameOverState(new GameOver()));
        } else {
            mariController.step(main, action, time);
            ghostController.step(main, action, time);
            batController.step(main, action, time);
            platformController.step(main, action, time);
            potionController.step(main, action, time);


            if (cameraCount < 2 && (getModel().getMari().getPosition().getX() == 100)){
                cameraController.step(main, action, time);
                cameraCount++;
            }
        }

    }

    public CameraController getCameraController() {
        return cameraController;
    }

    public MariController getMariController() {
        return mariController;
    }

    public BatEnemyController getBatController() {
        return batController;
    }

    public GhostEnemyController getGhostController() {
        return ghostController;
    }
}
