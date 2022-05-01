package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.actions.SwordTossAction;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.cards.CardENUMS;
import theHeroOfJustice.patches.combat.MagicCircuits;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class SwordToss extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Sword Toss - Projection. Deal X damage. Exhaust a card and gain Magic Circuits equal to its energy cost
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SwordToss.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;

    // /STAT DECLARATION/

    public SwordToss() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.tags.add(CardENUMS.PROJECTION);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Remove the Magic Circuits needed to play the card
        MagicCircuits.magicCircuitAmount.set(AbstractDungeon.player, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) - this.cost);

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new SwordTossAction(p));
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
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}