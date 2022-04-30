package theHeroOfJustice.cards;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theHeroOfJustice.DefaultMod;
import theHeroOfJustice.characters.TheDefault;

import javax.smartcardio.Card;
import java.util.ArrayList;

import static theHeroOfJustice.DefaultMod.makeCardPath;

public class BeginChant extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Begin Chant - Add a 0-cost "Rho Aias" card to your hand. Add a "Continue Chant" card to your draw pile. Exhaust.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(BeginChant.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;

    private ArrayList<AbstractCard> previewCardsList = new ArrayList<>();

    // /STAT DECLARATION/

    private float duration = 3.0F;
    private float timer;
    private int currentPreview;

    public BeginChant() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        exhaust = true;

        timer = duration;

        currentPreview = 0;
        this.previewCardsList.add(new RhoAias());
        previewCardsList.get(0).modifyCostForCombat(0);
        this.previewCardsList.add(new ContinueChant());

        this.cardsToPreview = previewCardsList.get(0);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new RhoAias();
        c.modifyCostForCombat(-1);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(this.previewCardsList.get(1).makeStatEquivalentCopy(), 1, true, true));
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

    @Override
    public void update() {
        super.update();

        //Only start rotating the preview if they're hovering the card
        if(hb.hovered){
            timer -= Gdx.graphics.getDeltaTime();
            //When they've hovered it long enough, go to the next card
            if(timer <= 0){
                //The % operator means it will wrap around once it reaches the end
                currentPreview = (currentPreview + 1) % previewCardsList.size();
                this.cardsToPreview = previewCardsList.get(currentPreview);
                timer = duration;
            }
        }
    }
}