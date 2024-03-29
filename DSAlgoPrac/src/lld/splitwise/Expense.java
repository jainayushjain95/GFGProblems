package lld.splitwise;

import java.util.List;
import java.util.UUID;

public abstract class Expense {

	private String id;
	private double amount;
	private User paidBy;
	private List<Split> splits;
	
	public Expense(double amount, User paidBy, List<Split> splits) {
		super();
		this.amount = amount;
		this.paidBy = paidBy;
		this.splits = splits;
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}
	public List<Split> getSplits() {
		return splits;
	}
	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}
	
	
	public abstract boolean isValidExpense();
	
}
