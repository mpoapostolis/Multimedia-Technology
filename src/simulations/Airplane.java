package simulations;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Airplane {
    private int realX;
    private int realY;
    private int currentSpeed;
    private int maxSpeed; // TODO
    private int currentAltitude = 0;
    private int fuel;
    private AirplaneType type;
    private Direction direction;
    private ImageView imgView;
    private Airport destination;
    private boolean reachedDestination = false;

    public void move() {
        switch (direction) {
            case North:
                realY -= 20;
                break;
            case East:
                realX += 20;
                break;
            case South:
                realY += 20;
                break;
            case West:
                realX -= 20;
                break;
        }
        Direction oldDirection = direction;
        if (getY() < destination.getY())
            direction = Direction.South;
        else if (getY() > destination.getY())
            direction = Direction.North;
        else if (getX() < destination.getX())
            direction = Direction.East;
        else if (getX() > destination.getX())
            direction = Direction.West;
        else
            reachedDestination = true;
        if (!oldDirection.equals(direction))
            loadImage();
    }

    public ImageView getImgView() {
        return imgView;
    }

    private void loadImage() {
        String imagePath = String.format("resources/%s_%s.png", type.getCode(), direction.getCode());
        System.out.println(imagePath);
        Image img = new Image(imagePath);
        imgView = new ImageView(img);
        imgView.minHeight(16);
        imgView.minWidth(16);
     }

    public Airplane(int x, int y, Direction direction, int fuel, AirplaneType type) {
        this.realX = 20*x;
        this.realY = 20*y;
        this.direction = direction;
        this.fuel = fuel;
        this.type = type;
        this.currentSpeed = type.getStartSpeed();
        loadImage();
    }


    public int getX() {
        return Math.round(realX/20);
    }

    public int getY() {
        return Math.round(realY/20);
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getCurrentAltitude() {
        return currentAltitude;
    }

    public void setCurrentAltitude(int currentAltitude) {
        this.currentAltitude = currentAltitude;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public AirplaneType getType() {
        return type;
    }

    public void setType(AirplaneType type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public boolean isReachedDestination() {
        return reachedDestination;
    }
}
