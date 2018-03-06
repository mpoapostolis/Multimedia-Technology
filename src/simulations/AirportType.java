package simulations;

import java.util.HashMap;
import java.util.Map;

public enum AirportType {

    Single(1),
    Jet(2),
    All(3);

    private final int type;
    private static Map map = new HashMap<>();

    AirportType(int type) {
        this.type = type;
    }

    static {
        for (AirportType airportType : AirportType.values())
            map.put(airportType.type, airportType);
    }

    public static AirportType of(int value) {
        return (AirportType) map.get(value);
    }

}

