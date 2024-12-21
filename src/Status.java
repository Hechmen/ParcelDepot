public enum Status {
    COLLECTED(" Collected"), UNCOLLECTED(" Uncollected");
    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
