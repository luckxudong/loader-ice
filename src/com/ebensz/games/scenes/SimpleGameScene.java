package com.ebensz.games.scenes;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.ebensz.games.R;
import com.ebensz.games.logic.story.Game;
import com.ebensz.games.model.Dir;
import com.ebensz.games.model.hand.ColoredHand;
import com.ebensz.games.ui.widget.DirPokerTiles;
import com.ebensz.games.utils.SleepUtils;
import ice.engine.EngineContext;
import ice.node.widget.Button;
import ice.node.widget.TextureGrid;
import ice.res.Res;

/**
 * User: Mike.Hu
 * Date: 11-11-28
 * Time: 下午4:54
 */
public class SimpleGameScene extends GameScene {
    private static final String TAG = SimpleGameScene.class.getSimpleName();

    public SimpleGameScene(Game game) {
        super(game);

        background = new TextureGrid(R.drawable.bg_game_1);
        addChild(background);
    }

    @Override
    public void showChuPai(Dir chuPaiDir, ColoredHand chuPai, boolean noShouPaiLeft) {

        showChuPai(chuPaiDir, chuPai);

        SleepUtils.sleep(noShouPaiLeft ? 2000 : 400);
        Log.i(TAG, "chuPai player: " + chuPaiDir + "--" + chuPai);
    }

    @Override
    public void showJiePai(Dir jiePaiDir, ColoredHand jiePai, Dir chuPaiDir, boolean noShouPaiLeft) {

        getPokerTiles(jiePaiDir).hideLastChuPai();

        SleepUtils.sleep(300);

        showChuPai(jiePaiDir, jiePai);

        SleepUtils.sleep(noShouPaiLeft ? 2000 : 400);
        Log.i(TAG, "jiePai player: " + jiePaiDir + "--" + jiePai);
    }

    private void showChuPai(Dir chuPaiDir, ColoredHand chuPai) {
        DirPokerTiles shouPaiTiles = null;

        switch (chuPaiDir) {
            case Outside:
                shouPaiTiles = outsidePokers;
                break;
            case Right:
                shouPaiTiles = rightPokers;
                break;
            case Left:
                shouPaiTiles = leftPokers;
                break;
        }

        shouPaiTiles.chuPai(chuPai);
    }

    public void showSelectContinueGame() {
        Bitmap normal = Res.getBitmap(R.drawable.start_game);
        Bitmap pressed = Res.getBitmap(R.drawable.start_game_press);

        Point pos = new Point(
                (EngineContext.getAppWidth() - normal.getWidth()) / 2,
                550
        );

        selectContinueGameBtn = new Button(normal, pressed);
        selectContinueGameBtn.setPos(pos.x, pos.y);
        addChild(selectContinueGameBtn);
    }

    public Button getSelectContinueGameBtn() {
        return selectContinueGameBtn;
    }

    private Button selectContinueGameBtn;
    private TextureGrid background;
}
