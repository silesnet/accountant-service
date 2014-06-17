package net.snet.accountant.bo;

import java.util.ArrayList;

public class BillPatch {

	private int id;
	private ArrayList<BillSynchronizedPatch> updates;

	public ArrayList<BillSynchronizedPatch> getUpdates() {
		return updates;
	}

	public void setUpdates(ArrayList<BillSynchronizedPatch> updates) {
		this.updates = updates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
