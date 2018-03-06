package simulations;

import java.util.List;
import java.util.stream.Collectors;

public class Flight {
    private int id;
    private int time;
    private int startId;
    private int endId;
    private String name;
    private AirplaneType type;
    private int speed;
    private int altitude;
    private int fuel;
    private Airplane airplane;

    /**
     * @param row
     */
    public Flight(String[] row) {
        this.id = Integer.parseInt(row[0]);
        this.time = Integer.parseInt(row[1]);
        this.startId = Integer.parseInt(row[2]);
        this.endId = Integer.parseInt(row[3]);
        this.name = row[4];
        this.type = AirplaneType.of(Integer.parseInt(row[5]));
        this.speed = Integer.parseInt(row[6]);
        this.altitude = Integer.parseInt(row[7]);
        this.fuel = Integer.parseInt(row[8]);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id + "}";
    }

    public Airplane getAirplane() {
        return airplane;
    }

    /**
     * @param airports
     * @return
     */
    public boolean isValid(List<Airport> airports) {
        if (type.getAltitude() < altitude || type.getFuel() < fuel || type.getSpeed() < speed) {
            System.out.println(String.format("Flight with id %s has invalid airplane type", id));
            return false;
        }
        Airport departureAirport = airports.stream().filter(a -> a.getId() == startId).collect(Collectors.toList()).get(0);
        this.airplane = new Airplane(departureAirport.getX(), departureAirport.getY(), departureAirport.getDirection(), fuel, type);
        Airport destinationAirport = airports.stream().filter(a -> a.getId() == endId).collect(Collectors.toList()).get(0);
        this.airplane.setDestination(destinationAirport);
        return airports.stream()
                .filter(a -> a.getId() == startId || a.getId() == endId)
                .allMatch(a -> a.isOpen() && a.supports(type));
    }

    public int getTime() {
        return time;
    }
}
