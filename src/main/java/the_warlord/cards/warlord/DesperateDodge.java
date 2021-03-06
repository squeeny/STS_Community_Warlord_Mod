package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.PosturePower;

public class DesperateDodge extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(DesperateDodge.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;
    private static final int COST = 1;
    private static final int BLOCK = 11;
    private static final int UPGRADE_PLUS_BLOCK = 4;

    public DesperateDodge() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(PosturePower.POWER_ID)) {
            addToBot(new GainBlockAction(p, p, block));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeDescription();
        }
    }

    @Override
    public boolean shouldGlowGold() {
        return !AbstractDungeon.player.hasPower(PosturePower.POWER_ID);
    }

}
