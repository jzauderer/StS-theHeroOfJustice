package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.cards.CardENUMS;
import theHeroOfJustice.patches.combat.MagicCircuits;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class SwordRain extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Sword Rain - Projection. Deal X damage to ALL enemies Y times
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SwordRain.class.getSimpleName());
    public static final String IMG = makeCardPath("SwordRain.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int TIMES = 3;
    private static final int UPGRADE_TIMES = 1;

    // /STAT DECLARATION/

    public SwordRain() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = TIMES;
        isMultiDamage = true;
        this.tags.add(CardENUMS.PROJECTION);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Remove the Magic Circuits needed to play the card
        MagicCircuits.magicCircuitAmount.set(AbstractDungeon.player, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) - this.cost);
        for(int i = 0; i < magicNumber; i++)
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        //Projection cards require Magic Circuit equal to the Energy cost of the card
        if(MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) < this.cost)
            return false;
        return super.canUse(p, m);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_TIMES);
            initializeDescription();
        }
    }
}