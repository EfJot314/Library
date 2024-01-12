package agh.edu.pl.weedesign.library.helpers;

public enum Categories {
    FANTASY,
    SCI_FI;

    @Override
    public String toString(){
        return switch (this){
            case FANTASY -> "Fantasy";
            case SCI_FI -> "Science Fiction";
        };
    }
}
