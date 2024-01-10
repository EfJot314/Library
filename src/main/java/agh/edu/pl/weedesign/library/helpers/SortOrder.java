package agh.edu.pl.weedesign.library.helpers;



public enum SortOrder {
    ASCENDING,
    DESCENDING;

    @Override
    public String toString() {
        return switch (this){
            case ASCENDING -> "Od A do Z";
            case DESCENDING -> "Od Z do A";
        };
    }
}
