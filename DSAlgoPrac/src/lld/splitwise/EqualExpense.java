package lld.splitwise;

import java.util.List;

public class EqualExpense extends Expense {

	public EqualExpense(double amount, User paidBy, List<Split> splits) {
		super(amount, paidBy, splits);
	}

	@Override
	public boolean isValidExpense() {
		return CommonUtility.isEmpty(getSplits());
	}

}
