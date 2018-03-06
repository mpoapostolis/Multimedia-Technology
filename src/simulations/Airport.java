package simulations;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Airport {
    private static Image img;

    static {
        String imagePath = "resources/airport.png";
        img = new Image(imagePath);
    }

    private int id;
    private int x;
    private int y;
    private String name;
    private Direction direction;
    private AirportType type;
    private boolean open;
    private ImageView imgView;

    /**
     * @param row
     */
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

    /**
     * @param planeType
     * @return
     */
    public boolean supports(AirplaneType planeType) {
        return type.equals(AirportType.All)
                || (type.equals(AirportType.Single) && planeType.equals(AirplaneType.Single))
                || (type.equals(AirportType.Jet) && !planeType.equals(AirplaneType.Single));
    }

    public int getId() {
        return id;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }


    public boolean isOpen() {
        return open;
    }


    public ImageView getImgView() {
        return imgView;
    }


}

