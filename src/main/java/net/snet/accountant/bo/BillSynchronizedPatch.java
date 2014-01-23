package net.snet.accountant.bo;

import java.sql.Timestamp;

public class BillSynchronizedPatch {

    private Timestamp synchronized_on;

    public Timestamp getSynchronized_on() {
        return synchronized_on;
    }

    public void setSynchronized_on(Timestamp synchronized_on) {
        this.synchronized_on = synchronized_on;
    }
}
