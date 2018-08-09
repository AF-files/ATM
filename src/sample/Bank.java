package sample;

public class Bank {
    private String bankName;
    private String branch;
    private int sortCode;

    public Bank(String bankName, String branch, int sortCode) {
        this.bankName = bankName;
        this.branch = branch;
        this.sortCode = sortCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranch() {
        return branch;
    }

    public int getSortCode() {
        return sortCode;
    }
}
