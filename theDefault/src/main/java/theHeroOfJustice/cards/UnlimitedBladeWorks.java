package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.cards.CardENUMS;
import theHeroOfJustice.patches.combat.MagicCircuits;
import theHeroOfJustice.powers.RapidProjectionsPower;
import theHeroOfJustice.powers.UBWPower;

import java.util.ArrayList;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class UnlimitedBladeWorks extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Unlimited Blade Works - At the start of your turn, gain x Magic Circuits. Projection cards cost 1 less energy
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(UnlimitedBladeWorks.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int AMOUNT = 1;
    private static final int CIRCUIT_GAIN = 3;

    // /STAT DECLARATION/


    public UnlimitedBladeWorks() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CIRCUIT_GAIN;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new UBWPower(p, p, AMOUNT), AMOUNT));
        MagicCircuits.magicCircuitAmount.set(AbstractDungeon.player, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) + CIRCUIT_GAIN);

        //Iterate through the cards in your hand and reduce the cost of the projections by 1 this turn
        //The power will reduce them as you draw them, so we just need to reduce them once here
        ArrayList<AbstractCard> cards = p.hand.group;
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).tags.contains(CardENUMS.PROJECTION))
                cards.get(i).setCostForTurn(cards.get(i).costForTurn - 1);
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}