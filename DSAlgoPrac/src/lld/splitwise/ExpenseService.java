package lld.splitwise;

import java.util.List;

public class ExpenseService {

	public static Expense createExpense(ExpenseType expenseType, List<Split> splits, User paidBy, double amountPaid) {
		switch(expenseType) {
			case EXACT:
				return new ExactExpense(amountPaid, paidBy, splits);
			case PERCENT:
				for(Split split : splits) {
					PercentSplit percentSplit = (PercentSplit)split;
					split.setAmount((percentSplit.getPercent() * amountPaid) / 100);
				}
				return new PercentExpense(amountPaid, paidBy, splits);
			case EQUAL:
				for(Split split : splits) {
					split.setAmount(amountPaid/splits.size());
				}
				return new EqualExpense(amountPaid, paidBy, splits);
			default:
				return null;
		}
	}
	
}
