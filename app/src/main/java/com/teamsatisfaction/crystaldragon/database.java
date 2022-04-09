package com.teamsatisfaction.crystaldragon;

public class database {
    private String dataPoint01;
    private int dataPoint02;

    // constructor
    public database(String dataPoint01, int dataPoint02) {
        this.dataPoint01 = dataPoint01;
        this.dataPoint02 = dataPoint02;
    }

    //toString method (for printing, but you knew that already didn't you lol ;D )
    @Override
    public String toString() {
        return "datahold{" +
                "dataPoint01='" + dataPoint01 + '\'' +
                ", dataPoint02=" + dataPoint02 +
                '}';
    }


    // Getters and setters

    public String getDataPoint01() {
        return dataPoint01;
    }

    public void setDataPoint01(String dataPoint01) {
        this.dataPoint01 = dataPoint01;
    }

    public int getDataPoint02() {
        return dataPoint02;
    }

    public void setDataPoint02(int dataPoint02) {
        this.dataPoint02 = dataPoint02;
    }
}
