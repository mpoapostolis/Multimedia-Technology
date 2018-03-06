package simulations;

import java.util.HashMap;
import java.util.Map;

public enum AirplaneType {
    Single(1, 110, 280, 8000, "small", 60),
    Turboprop(2, 220, 4200, 16000, "middle", 100),
    Jet(3, 280, 16000, 28000, "big", 140);

    private final int type;
    private final int speed;
    private final int fuel;
    private final int altitude;
    private final String code;
    private final int startSpeed;
    private static Map map = new HashMap<>();

    AirplaneType(int type, int speed, int fuel, int altitude, String code, int startSpeed) {
        this.type = type;
        this.speed = speed;
        this.fuel = fuel;
        this.altitude = altitude;
        this.code = code;
        this.startSpeed = startSpeed;
    }

    static {
        for (AirplaneType airportType : AirplaneType.values())
            map.put(airportType.type, airportType);
    }

    public static AirplaneType of(int value) {
        return (AirplaneType) map.get(value);
    }

    public int getSpeed() {
        return speed;
    }

    public int getFuel() {
        return fuel;
    }

    public int getAltitude() {
        return altitude;
    }

    public String getCode() {
        return code;
    }

    public int getStartSpeed() {
        return startSpeed;
    }
}

