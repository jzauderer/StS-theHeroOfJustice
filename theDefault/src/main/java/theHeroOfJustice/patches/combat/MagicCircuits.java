package theHeroOfJustice.patches.combat;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theHeroOfJustice.patches.DefaultInsertPatch;

@SpirePatch(clz = AbstractPlayer.class, method = SpirePatch.CLASS)
public class MagicCircuits {
    public static SpireField<Integer> magicCircuitAmount = new SpireField<>(() -> 0);
    public static SpireField<Integer> magicCircuitPerTurn = new SpireField<>(() -> 0);
}