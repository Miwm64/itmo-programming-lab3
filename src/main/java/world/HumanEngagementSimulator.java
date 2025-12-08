package world;

import creatures.Buff;
import creatures.BuffType;
import creatures.Human;
import creatures.HumanFaceExpression;
import locations.Location;

public class HumanEngagementSimulator {
    static void lead(Human leader, Human human, Location location){
        leader.move(location);
        leader.changeExhaustion(5);
        human.move(location);
    }

    static void avoid(Human initiator, Human human){
        human.setFaceExpression(HumanFaceExpression.ANGRY);
        initiator.changeExhaustion(5);
        human.addKnownHuman(initiator);
        initiator.addKnownHuman(human);
    }

    static void hold(Human held, Human holder){
        held.addBuff(new Buff(BuffType.OVERLOAD, 10));
        held.changeExhaustion(5);
        holder.changeExhaustion(-2);
    }

    static void defectFrom(Human held, Human holder){
        held.removeBuff(new Buff(BuffType.OVERLOAD, 10));
    }
}
