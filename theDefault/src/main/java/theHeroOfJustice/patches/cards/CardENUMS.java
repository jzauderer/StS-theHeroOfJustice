package theHeroOfJustice.patches.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

/*
 * Patches have a pretty detailed documentation. Go check em out here:
 *
 *  https://github.com/kiooeht/ModTheSpire/wiki/SpirePatch
 */

@SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
public class CardENUMS {
    @SpireEnum
    public static AbstractCard.CardTags PROJECTION;
}

