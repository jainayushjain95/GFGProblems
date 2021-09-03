package lld.splitwise;

import java.util.List;

public class ExactExpense extends Expense {
	
	public ExactExpense(double amount, User paidBy, List<Split> splits) {
		super(amount, paidBy, splits);
	}

	@Override
	public boolean isValidExpense() {
		boolean isValidExpense = CommonUtility.isEmpty(getSplits());
		if(isValidExpense) {
			double totalAmount = this.getAmount();
			double amount = 0;
			for(Split split : getSplits()) {
				amount += ((ExactSplit)split).getAmount();
			}
			isValidExpense = totalAmount == amount;
		}
		return isValidExpense;
	}
}
