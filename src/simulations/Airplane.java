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
    private boolean collide;
    private AirplaneType type;
    private Direction direction;
    private ImageView imgView;
    private Airport destination;
    private boolean reachedDestination = false;
    private boolean isCrashed = false;

    /**
     * @param x
     * @param y
     * @param direction
     * @param fuel
     * @param type
     */

    public Airplane(int x, int y, Direction direction, int fuel, AirplaneType type) {
        this.realX = 20 * x;
        this.realY = 20 * y;
        this.direction = direction;
        this.fuel = fuel;
        this.type = type;
        this.currentSpeed = type.getStartSpeed();

        loadImage();
    }

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
        System.out.println(type.getFuelConsumption());
        Direction oldDirection = direction;
        if (getY() < destination.getY()) {
            fuel -= type.getFuelConsumption();
            direction = Direction.South;
        } else if (getY() > destination.getY()) {
            fuel -= type.getFuelConsumption();
            direction = Direction.North;
        } else if (getX() < destination.getX()) {
            fuel -= type.getFuelConsumption();
            direction = Direction.East;
        } else if (getX() > destination.getX()) {
            fuel -= type.getFuelConsumption();
            direction = Direction.West;
        } else {
            reachedDestination = true;
        }
        if (fuel < 0) isCrashed = true;
        System.out.println(Integer.toString(fuel));
        if (!oldDirection.equals(direction))
            loadImage();
    }

    /**
     * @return
     */
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

    public int getX() {
        return Math.round(realX / 20);
    }

    public int getY() {
        return Math.round(realY / 20);
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public boolean isReachedDestination() {
        return reachedDestination;
    }

    public boolean isCollide() {
        return collide;
    }

    public void setCollide(boolean collide) {
        this.collide = collide;
    }

    public boolean isCrashed() {
        return isCrashed;
    }

}
