package theHeroOfJustice.ui;
import basemod.ClickableUIElement;
import basemod.devcommands.draw.Draw;
import basemod.helpers.UIElementModificationHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.combat.MagicCircuits;
import theHeroOfJustice.util.TextureLoader;

import static theHeroOfJustice.DefaultMod.makeID;

public class MagicCircuitUI extends ClickableUIElement {
    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());
    private static final float X_OFF = 0f;
    private static final float Y_OFF = AbstractDungeon.overlayMenu.energyPanel.current_y + 60f;
    private static final float HB_WIDTH = 175f;
    private static final float HB_HEIGHT = 104f;
    private static final float COUNT_X = 48.0F * Settings.scale;
    private static final float COUNT_Y = -16.0F * Settings.scale;
    private static final float DECK_TIP_X = 32F * Settings.scale;
    private static final float DECK_TIP_Y = 256.0F * Settings.scale;
    private static final Texture MagicCircuitTexture = TextureLoader.getTexture("theHeroOfJusticeResources/images/ui/magic_circuit.png");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("MagicCircuits"));
    public static final String[] TEXT = uiStrings.TEXT;



    public MagicCircuitUI(){
        super((Texture) null,
                0f,
                Y_OFF,
                HB_WIDTH,
                HB_HEIGHT);
    }

    @Override
    public void render(SpriteBatch sb){
        if(AbstractDungeon.player.chosenClass == TheDefault.Enums.THE_HERO_OF_JUSTICE || MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) > 0){
            if (!AbstractDungeon.overlayMenu.combatDeckPanel.isHidden) {
                float x = 0;
                float y = hitbox.y;
                sb.setColor(Color.WHITE);
                sb.draw(MagicCircuitTexture, x, y);

                String msg = Integer.toString(MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player));
                sb.setColor(Color.WHITE);
                FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, msg, x + COUNT_X, y + COUNT_Y);


                hitbox.update();
                hitbox.render(sb);
                if (hitbox.hovered && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp) {
                    TipHelper.renderGenericTip(x + DECK_TIP_X, y + DECK_TIP_Y, TEXT[0], TEXT[1]);
                }
            }
        }
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    protected void onHover() {
        logger.info("Hovered Magic Circuits");
    }

    @Override
    protected void onUnhover() {
    }

    @Override
    protected void onClick() {
    }
}
