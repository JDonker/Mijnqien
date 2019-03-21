package com.Mijnqien;

import java.time.LocalDate;

public class DagelijkseCheck implements Runnable{

	@Override
	public void run() {
		
		LocalDate vandaag = LocalDate.now();
		LocalDate evm = LocalDate.of(vandaag.getYear(), vandaag.getMonth(), 1);
		LocalDate evm2 = LocalDate.of(vandaag.getYear(), vandaag.plusMonths(1).getMonth(), 1);
		
		if(vandaag.isEqual(evm2.minusWeeks(1)))
			
		if(vandaag.isEqual(evm2.minusDays(2)))

		if(vandaag.isEqual(evm))
		
		if(vandaag.isEqual(evm.plusDays(3))) {}
		
		System.out.println(evm2.minusWeeks(1));
		System.out.println(evm2.minusDays(2));
		System.out.println(evm.toString());
		System.out.println(evm.plusDays(3).toString());
		
	}

}
