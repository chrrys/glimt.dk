public class Speed {
    private int deltaX;
    private int deltaY;

    public Speed(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public String toString() {
        return String.format("(Δx=%d,Δy=%d)", deltaX, deltaY);
    }

    public int getKey() {
        return deltaX | (deltaY << 8);
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public Speed[] getPossibleNextSpeeds() {
        Speed[] speeds = new Speed[]{
                new Speed(deltaX - 1, deltaY - 1),
                new Speed(deltaX + 0, deltaY - 1),
                new Speed(deltaX + 1, deltaY - 1),

                new Speed(deltaX - 1, deltaY + 0),
                new Speed(deltaX + 0, deltaY + 0),
                new Speed(deltaX + 1, deltaY + 0),

                new Speed(deltaX - 1, deltaY + 1),
                new Speed(deltaX + 0, deltaY + 1),
                new Speed(deltaX + 1, deltaY + 1)
        };
        return speeds;
    }

    public String getRepresentation() {
        int offsetX = deltaX == 0 ? 1 : (deltaX > 0 ? 2 : 0);
        int offsetY = deltaY == 0 ? 1 : (deltaY > 0 ? 2 : 0);
        int offset = offsetY * 3 + offsetX;
        return "↖↑↗←•→↙↓↘".substring(offset, offset + 1);
    }
}

