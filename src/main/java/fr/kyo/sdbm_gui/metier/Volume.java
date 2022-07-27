package fr.kyo.sdbm_gui.metier;

public class Volume {
    private int volume;
    private String libelle;


    public Volume(){
        volume = 0;
        libelle = "";
    }
    public Volume(int volume, String libelle) {
        this.volume = volume;
        this.libelle = libelle;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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
