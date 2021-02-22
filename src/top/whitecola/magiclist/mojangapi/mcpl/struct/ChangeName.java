package top.whitecola.magiclist.mojangapi.mcpl.struct;

public class ChangeName {

    private String name;
    private long changedToAt = -1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getChangedToAt() {
        return changedToAt;
    }

    public void setChangedToAt(long changedToAt) {
        this.changedToAt = changedToAt;
    }
}

