package Model;

import java.util.Date;

public class History {
    private Transaction transaction;
    private Date date;

    public History(Transaction transaction, Date date) {
        this.transaction = transaction;
        this.date = date;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
