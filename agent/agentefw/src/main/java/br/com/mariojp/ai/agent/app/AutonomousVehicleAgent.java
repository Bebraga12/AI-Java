package br.com.mariojp.ai.agent.app;

import java.util.List;
import java.util.Random;

import br.com.mariojp.ai.agent.AgentFactory;
import br.com.mariojp.ai.agent.AgentModel;
import br.com.mariojp.ai.agent.IAgent;
import br.com.mariojp.ai.agent.INode;
import br.com.mariojp.ai.agent.exception.EmptyBorderException;

public class AutonomousVehicleAgent {

    public static void main(String[] args) {
        TrafficState startState = new TrafficState("Start", 100, 0);

        String[] destinations = {
                "Museum", "Cinema", "Park", "Mall", "Library", "Hospital", "School",
                "University", "Stadium", "Airport", "Restaurant", "TrainStation", "BusTerminal",
                "Supermarket", "Beach", "Zoo", "ResidentialArea", "Downtown", "PoliceStation",
                "FireStation", "ShoppingCenter", "OfficeBuilding", "ResidentialBlock",
                "DowntownPlaza", "MuseumOfArt", "UniversityCampus", "ConcertHall", "LibraryCenter",
                "InternationalAirport"
        };

        Random random = new Random();
        String randomDestination = destinations[random.nextInt(destinations.length)];
        TrafficState goalState = new TrafficState(randomDestination, 0, 0);
        System.out.println("Random destination selected: " + randomDestination);

        AgentModel model = new AgentModel();
        model.setInitState(startState);
        model.addObjective(goalState);
        model.setType(IAgent.START_SEARCH);

        TrafficFunctions functions = new TrafficFunctions();
        model.setFunctions(functions);

        model.addAction("MoveToMuseum", new MoveAction("Museum", 18, 9));
        model.addAction("MoveToCinema", new MoveAction("Cinema", 26, 11));
        model.addAction("MoveToPark", new MoveAction("Park", 10, 5));
        model.addAction("MoveToMall", new MoveAction("Mall", 15, 8));
        model.addAction("MoveToLibrary", new MoveAction("Library", 20, 10));
        model.addAction("MoveToHospital", new MoveAction("Hospital", 12, 6));
        model.addAction("MoveToSchool", new MoveAction("School", 18, 9));
        model.addAction("MoveToUniversity", new MoveAction("University", 25, 12));
        model.addAction("MoveToStadium", new MoveAction("Stadium", 7, 3));
        model.addAction("MoveToAirport", new MoveAction("Airport", 22, 14));
        model.addAction("MoveToMuseum", new MoveAction("Museum", 30, 16));
        model.addAction("MoveToRestaurant", new MoveAction("Restaurant", 17, 8));
        model.addAction("MoveToTrainStation", new MoveAction("TrainStation", 9, 4));
        model.addAction("MoveToBusTerminal", new MoveAction("BusTerminal", 14, 7));
        model.addAction("MoveToSupermarket", new MoveAction("Supermarket", 21, 10));
        model.addAction("MoveToBeach", new MoveAction("Beach", 28, 13));
        model.addAction("MoveToZoo", new MoveAction("Zoo", 6, 3));
        model.addAction("MoveToCinema", new MoveAction("Cinema", 26, 11));
        model.addAction("MoveToResidentialArea", new MoveAction("ResidentialArea", 16, 9));
        model.addAction("MoveToDowntown", new MoveAction("Downtown", 23, 15));
        model.addAction("MoveToPoliceStation", new MoveAction("PoliceStation", 5, 2));
        model.addAction("MoveToFireStation", new MoveAction("FireStation", 10, 4));
        model.addAction("MoveToShoppingCenter", new MoveAction("ShoppingCenter", 20, 10));
        model.addAction("MoveToOfficeBuilding", new MoveAction("OfficeBuilding", 15, 8));
        model.addAction("MoveToResidentialBlock", new MoveAction("ResidentialBlock", 12, 6));
        model.addAction("MoveToDowntownPlaza", new MoveAction("DowntownPlaza", 25, 12));
        model.addAction("MoveToMuseumOfArt", new MoveAction("MuseumOfArt", 18, 9));
        model.addAction("MoveToUniversityCampus", new MoveAction("UniversityCampus", 22, 11));
        model.addAction("MoveToConcertHall", new MoveAction("ConcertHall", 30, 16));
        model.addAction("MoveToLibraryCenter", new MoveAction("LibraryCenter", 8, 4));
        model.addAction("MoveToInternationalAirport", new MoveAction("InternationalAirport", 35, 18));

        IAgent agent = AgentFactory.createAgent(model);

        try {
            INode solutionNode = agent.function();
            List<INode> path = agent.getPath(solutionNode);

            System.out.println("Best path found:");
            for (INode node : path) {
                System.out.println(node.getState().toString());
            }
        } catch (EmptyBorderException e) {
            System.out.println("No solution found: " + e.getMessage());
        }
    }
}