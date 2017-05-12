public enum CellType {
    START, FINISH, WALL, PATH;

    static CellType getCellType(char c) {
        switch (c) {
            case 'O':
                return WALL;
            case ' ':
                return PATH;
            case 'S':
                return START;
            case 'F':
                return FINISH;
        }
        return null;
    }

    public String getRepresentation() {
        switch (this) {
            case WALL:
                return "█";
            case PATH:
                return "░";
            case START:
                return "S";
            case FINISH:
                return "F";
        }
        return null;
    }

}
