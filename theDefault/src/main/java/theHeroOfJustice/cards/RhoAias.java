package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.cards.CardENUMS;
import theHeroOfJustice.patches.combat.MagicCircuits;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class RhoAias extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Sleep it off - Gain X block and Y Magic Circuit
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(RhoAias.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int CIRCUIT_MULTIPLIER = 2;
    private static final int UPGRADE_PLUS_CIRCUIT_MULTIPLIER = 1;


    // /STAT DECLARATION/


    public RhoAias() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CIRCUIT_MULTIPLIER;
        this.tags.add(CardENUMS.PROJECTION);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Remove the Magic Circuits needed to play the card
        MagicCircuits.magicCircuitAmount.set(AbstractDungeon.player, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) - this.cost);
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) * magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        //Projection cards require Magic Circuit equal to the Energy cost of the card
        if(MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) < this.cost)
            return false;
        return super.canUse(p, m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_CIRCUIT_MULTIPLIER);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
