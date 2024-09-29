package br.com.mariojp.ai.agent.app;

import br.com.mariojp.ai.agent.IState;

class TrafficState implements IState {
    private String location;
    private double energy;
    private double time;

    public TrafficState(String location, double energy, double time) {
        this.location = location;
        this.energy = energy;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public double getEnergy() {
        return energy;
    }

    public double getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TrafficState other = (TrafficState) obj;
        return this.location.equals(other.location);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new TrafficState(this.location, this.energy, this.time);
    }

    @Override
    public String toString() {
        return "Location: " + this.location + ", Energy: " + this.energy + ", Time: " + this.time;
    }
}