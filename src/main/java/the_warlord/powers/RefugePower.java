package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RefugePower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(RefugePower.class);
    public static final String POWER_ID = STATIC.ID;

    public RefugePower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        int reactionBonus = 0;
        if (owner.hasPower(ReactionTimePower.POWER_ID)) {
            reactionBonus = owner.getPower(ReactionTimePower.POWER_ID).amount;
        }

        addToBot(new GainBlockAction(owner, this.amount + reactionBonus));
    }

    @Override
    public AbstractPower makeCopy() {
        return new RefugePower(owner, amount);
    }
}