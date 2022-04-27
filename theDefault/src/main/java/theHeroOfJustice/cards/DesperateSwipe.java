package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.cards.CardENUMS;
import theHeroOfJustice.patches.combat.MagicCircuits;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class DesperateSwipe extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Desperate Swipe - Deal X damage. If you have 3 or fewer Magic Circuits, gain 3 Magic Circuits
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DesperateSwipe.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int CIRCUIT_CHECK = 2;
    private static final int CIRCUIT_GAIN = 2;

    // /STAT DECLARATION/

    public DesperateSwipe() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = CIRCUIT_GAIN;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) <= CIRCUIT_CHECK)
            MagicCircuits.magicCircuitAmount.set(AbstractDungeon.player, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) + CIRCUIT_GAIN);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void triggerOnGlowCheck() {
        if(MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player) <= CIRCUIT_CHECK)
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
        else
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
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