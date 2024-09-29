package br.com.mariojp.ai.agent.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.mariojp.ai.agent.IState;
import br.com.mariojp.ai.agent.action.AbstractAction;
import br.com.mariojp.ai.agent.exception.ImpossibleActionException;

class MoveAction extends AbstractAction {
    private String nextLocation;
    private double baseEnergyCost;
    private double baseTimeCost;

    private boolean trafficHeavy;
    private boolean isRaining;
    private boolean redLight;

    public MoveAction(String nextLocation, double baseEnergyCost, double baseTimeCost) {
        this.nextLocation = nextLocation;
        this.baseEnergyCost = baseEnergyCost;
        this.baseTimeCost = baseTimeCost;

        Random random = new Random();
        this.trafficHeavy = random.nextBoolean();  
        this.isRaining = random.nextBoolean();     
        this.redLight = random.nextBoolean();      
    }

    @Override
    public List<IState> execute(IState currentState) throws ImpossibleActionException {
        TrafficState state = (TrafficState) currentState;
        double modifiedEnergyCost = baseEnergyCost;
        double modifiedTimeCost = baseTimeCost;

        if (trafficHeavy) {
            modifiedTimeCost += 5; 
            System.out.println("Heavy traffic detected. Increasing time cost.");
        }
        if (isRaining) {
            modifiedEnergyCost += 2; 
            modifiedTimeCost += 3;   
            System.out.println("Rain detected. Increasing energy and time cost.");
        }
        if (redLight) {
            modifiedTimeCost += 2; 
            System.out.println("Red light detected. Increasing time cost.");
        }

        if (state.getEnergy() < modifiedEnergyCost) {
            throw new ImpossibleActionException("Not enough energy to move");
        }

        List<IState> newStateList = new ArrayList<>();
        newStateList.add(new TrafficState(nextLocation, state.getEnergy() - modifiedEnergyCost, state.getTime() + modifiedTimeCost));
        return newStateList;
    }

    @Override
    public List<IState> revert(IState state) throws ImpossibleActionException {
        return null;  
    }
}
