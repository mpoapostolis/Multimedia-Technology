package simulations;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Airport {
    private int id;
    private int x;
    private int y;
    private String name;
    private Direction direction;
    private AirportType type;
    private boolean open;
    private ImageView imgView;
    private static Image img;

    static {
        String imagePath = "resources/airport.png";
        img = new Image(imagePath);
    }

    public Airport(String[] row) {
        this.id = Integer.parseInt(row[0]);
        this.y = Integer.parseInt(row[1]);
        this.x = Integer.parseInt(row[2]);
        this.name = row[3];
        this.direction = Direction.of((Integer.parseInt(row[4])));
        this.type = AirportType.of(Integer.parseInt(row[5]));
        this.open = Integer.parseInt(row[6]) == 1;
        this.imgView = new ImageView(img);
        imgView.minHeight(16);
        imgView.minWidth(16);
    }

    public boolean supports(AirplaneType planeType) {
        return type.equals(AirportType.All)
                || (type.equals(AirportType.Single) && planeType.equals(AirplaneType.Single))
                || (type.equals(AirportType.Jet) && !planeType.equals(AirplaneType.Single));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public AirportType getType() {
        return type;
    }

    public void setType(AirportType type) {
        this.type = type;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }
}

