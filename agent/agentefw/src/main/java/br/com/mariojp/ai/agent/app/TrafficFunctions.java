package br.com.mariojp.ai.agent.app;

import java.util.ArrayList;
import java.util.List;

import br.com.mariojp.ai.agent.IFunctions;
import br.com.mariojp.ai.agent.INode;
import br.com.mariojp.ai.agent.IState;

class TrafficFunctions implements IFunctions {
    private List<IState> objectives = new ArrayList<>();

    @Override
    public double g(INode node) {
        TrafficState state = (TrafficState) node.getState();
        return state.getTime();
    }

    @Override
    public double h(INode node) {
        return 0; // Heurística simples
    }

    @Override
    public double calculateUtility(INode node) {
        return g(node) + h(node);
    }

    @Override
    public boolean objectiveFunction(IState state) {
        return objectives.contains(state);
    }

    @Override
    public void setObjectives(List<IState> objectives) {
        this.objectives = objectives;
    }

    @Override
    public List<IState> getObjectives() {
        return this.objectives;
    }
}

