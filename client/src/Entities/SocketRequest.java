package Entities;

public class SocketRequest {
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String option;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    private byte typeConnection;

    public byte getTypeConnection() {
        return typeConnection;
    }

    public void setTypeConnection(byte typeConnection) {
        this.typeConnection = typeConnection;
    }

}
