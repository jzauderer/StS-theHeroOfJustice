package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.combat.MagicCircuits;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class StrengthenedLegs extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strengthened Legs - Gain X block. Costs 1 Magic Circuit.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(StrengthenedLegs.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 12;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    private static final int CIRCUIT_COST = 1;


    // /STAT DECLARATION/


    public StrengthenedLegs() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = CIRCUIT_COST;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicCircuits.magicCircuitAmount.set(AbstractDungeon.player, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) - CIRCUIT_COST);
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        //Projection cards require Magic Circuit equal to the Energy cost of the card
        if(MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) < CIRCUIT_COST)
            return false;
        return super.canUse(p, m);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
