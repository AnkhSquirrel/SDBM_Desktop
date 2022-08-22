package fr.kyo.sdbm_gui.metier;

public class Volume {
    private int volumeVal;
    private String libelle;


    public Volume(){
        volumeVal = 0;
        libelle = "";
    }
    public Volume(int volumeVal, String libelle) {
        this.volumeVal = volumeVal;
        this.libelle = libelle;
    }

    public int getVolume() {
        return volumeVal;
    }

    public void setVolume(int volumeVal) {
        this.volumeVal = volumeVal;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
