package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.cards.CardENUMS;
import theHeroOfJustice.patches.combat.MagicCircuits;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class Reflection extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Reflection - Gain X block. If your last-played card was a Projection, gain 1 energy
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Reflection.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public Reflection() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() > 1)
            if(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2).tags.contains(CardENUMS.PROJECTION))
                p.gainEnergy(1);
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    @Override
    public void triggerOnGlowCheck() {
        if(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() > 0)
            if(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).tags.contains(CardENUMS.PROJECTION))
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
        else
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
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
