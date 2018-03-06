package simulations;

import java.util.HashMap;
import java.util.Map;

public enum AirplaneType {
    Single(1, 110, 280, 8000, "small", 60, 3),
    Turboprop(2, 220, 4200, 16000, "middle", 100, 9),
    Jet(3, 280, 16000, 28000, "big", 140, 15);

    private static Map map = new HashMap<>();

    static {
        for (AirplaneType airportType : AirplaneType.values())
            map.put(airportType.type, airportType);
    }

    private final int type;
    private final int speed;
    private final int fuel;
    private final int altitude;
    private final String code;
    private final int startSpeed;
    private final int fuelConsumption;

    /**
     *
     * @param type
     * @param speed
     * @param fuel
     * @param altitude
     * @param code
     * @param startSpeed
     * @param fuelConsumption
     */
    AirplaneType(int type, int speed, int fuel, int altitude, String code, int startSpeed, int fuelConsumption) {
        this.type = type;
        this.speed = speed;
        this.fuel = fuel;
        this.altitude = altitude;
        this.code = code;
        this.startSpeed = startSpeed;
        this.fuelConsumption= fuelConsumption;
    }

    /**
     * @param value
     * @return
     */
    public static AirplaneType of(int value) {
        return (AirplaneType) map.get(value);
    }

    /**
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * @return
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * @return
     */
    public int getStartSpeed() {
        return startSpeed;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }
}

