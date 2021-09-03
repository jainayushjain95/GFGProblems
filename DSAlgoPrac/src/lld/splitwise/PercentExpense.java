package lld.splitwise;

import java.util.List;

public class PercentExpense extends Expense {

	public PercentExpense(double amount, User paidBy, List<Split> splits) {
		super(amount, paidBy, splits);
	}

	@Override
	public boolean isValidExpense() {
		boolean isValidExpense = CommonUtility.isEmpty(getSplits());
		if(isValidExpense) {
			double totalPercentage = 100;
			double percentage = 0;
			for(Split split : getSplits()) {
				percentage += ((PercentSplit)(split)).getPercent();
			} 
			isValidExpense = totalPercentage == percentage;
		}
		return isValidExpense;
	}
}
