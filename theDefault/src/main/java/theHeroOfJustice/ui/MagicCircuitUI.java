package theHeroOfJustice.ui;
import basemod.ClickableUIElement;
import basemod.helpers.UIElementModificationHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.MembershipCard;
import com.megacrit.cardcrawl.relics.SmilingMask;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.shop.StoreRelic;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.vfx.ShopSpeechBubble;
import com.megacrit.cardcrawl.vfx.SpeechTextEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.combat.MagicCircuits;
import theHeroOfJustice.util.TextureLoader;

import static theHeroOfJustice.DefaultMod.makeRelicOutlinePath;

public class MagicCircuitUI extends ClickableUIElement {
    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());
    private static final float COUNT_X = 48.0F * Settings.scale;
    private static final float COUNT_Y = -16.0F * Settings.scale;
    private static final Texture MagicCircuitTexture = TextureLoader.getTexture("theHeroOfJusticeResources/images/ui/magic_circuit.png");


    public MagicCircuitUI(){
        super(MagicCircuitTexture,
                10f,
                Settings.HEIGHT * 0.8f,
                175f,
                101f);
    }

    @Override
    public void render(SpriteBatch sb){
        if(AbstractDungeon.player.chosenClass == TheDefault.Enums.THE_HERO_OF_JUSTICE || MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) > 0){
            if (!AbstractDungeon.overlayMenu.combatDeckPanel.isHidden) {
                float x = hitbox.x + hitbox.width / 2f;
                float y = hitbox.y + hitbox.height / 2f;
                sb.setColor(Color.WHITE);

                String msg = Integer.toString(MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player));
                sb.setColor(Color.WHITE);
                FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, msg, x + COUNT_X, y + COUNT_Y);

                hitbox.render(sb);
//                if (hitbox.hovered && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp) {
//                    TipHelper.renderGenericTip(x + DECK_TIP_X, y + DECK_TIP_Y, TEXT[0], TEXT[1]);
//                }
            }
        }
    }

    @Override
    protected void onHover() {
    }

    @Override
    protected void onUnhover() {
    }

    @Override
    protected void onClick() {
    }
}
