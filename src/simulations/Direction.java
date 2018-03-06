package simulations;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    North (1, "n"),
    East (2, "e"),
    South (3, "s"),
    West (4, "w");

    private final int directionNum;
    private final String code;
    private static Map map = new HashMap<>();

    Direction(int directionNum, String code) {
        this.directionNum = directionNum;
        this.code = code;

    }

    static {
        for (Direction direction : Direction.values())
            map.put(direction.directionNum, direction);
    }

    public static Direction of(int value) {
        return (Direction) map.get(value);
    }

    public String getCode() {
        return code;
    }

}
