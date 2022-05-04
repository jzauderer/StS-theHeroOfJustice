package theHeroOfJustice.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;
import theHeroOfJustice.patches.combat.MagicCircuits;

import java.util.ArrayList;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class Archery extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Archery - Deal X damage. If there is only one enemy, deal X damage again.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Archery.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;

    // /STAT DECLARATION/

    public Archery() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if(oneEnemyLeft())
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void triggerOnGlowCheck() {
        if(oneEnemyLeft())
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
        else
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    private boolean oneEnemyLeft(){
        ArrayList<AbstractMonster> monList = AbstractDungeon.getCurrRoom().monsters.monsters;
        int monsterCount = 0;
        for(int i = 0; i < monList.size(); i++){
            AbstractMonster mon = monList.get(i);
            if(!(mon.isDead || mon.isDying || mon.halfDead || mon.escaped))
                monsterCount++;
        }
        if(monsterCount == 1)
            return true;
        return false;
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