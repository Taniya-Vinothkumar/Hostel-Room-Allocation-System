package model;

public class Hostel {

    private int hostelId;
    private String hostelName;
    private String hostelType;
    private String block;

    public Hostel() {
    }

    public Hostel(int hostelId, String hostelName,
                  String hostelType, String block) {

        this.hostelId = hostelId;
        this.hostelName = hostelName;
        this.hostelType = hostelType;
        this.block = block;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}