package tk.ontes.past;

public enum Side {
    TOP, BOTTOM, LEFT, RIGHT;

    public static Side getOpposite(Side side) {
        switch(side) {
            case TOP:
                return Side.BOTTOM;
            case  BOTTOM:
                return Side.TOP;
            case LEFT:
                return Side.RIGHT;
            case RIGHT:
                return Side.LEFT;
            default:
                return side;
        }
    }
}
