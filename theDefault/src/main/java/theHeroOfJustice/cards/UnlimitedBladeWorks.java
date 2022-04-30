package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.combat.MagicCircuits;
import theHeroOfJustice.powers.RapidProjectionsPower;
import theHeroOfJustice.powers.UBWPower;

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
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

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