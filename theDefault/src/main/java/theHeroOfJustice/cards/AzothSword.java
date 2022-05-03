package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.combat.MagicCircuits;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class AzothSword extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Azoth Sword - Deal X damage. Deal damage equal to your Magic Circuits to all enemies
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(AzothSword.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int DAMAGE_PER_CIRCUIT = 1;

    // /STAT DECLARATION/

    public AzothSword() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DAMAGE_PER_CIRCUIT;
        isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, MagicCircuits.magicCircuitAmount.get(AbstractDungeon.player), damageTypeForTurn, AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public void applyPowers()
    {
        magicNumber = baseMagicNumber;

        int tmp = baseDamage;
        baseDamage = baseMagicNumber;

        super.applyPowers();

        magicNumber = damage;
        baseDamage = tmp;

        super.applyPowers();

        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        magicNumber = baseMagicNumber;

        int tmp = baseDamage;
        baseDamage = baseMagicNumber;

        super.calculateCardDamage(mo);

        magicNumber = damage;
        baseDamage = tmp;

        super.calculateCardDamage(mo);

        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }

    @Override
    public void onMoveToDiscard()
    {
        magicNumber = baseMagicNumber;
        isMagicNumberModified = false;
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