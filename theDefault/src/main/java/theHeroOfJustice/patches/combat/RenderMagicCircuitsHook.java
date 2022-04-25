package theHeroOfJustice.patches.combat;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theHeroOfJustice.DefaultMod;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "render"
)
public class RenderMagicCircuitsHook {
    public static void Postfix(EnergyPanel __instance, SpriteBatch spriteBatch) {
        DefaultMod.renderMagicCircuits(spriteBatch);
    }
}