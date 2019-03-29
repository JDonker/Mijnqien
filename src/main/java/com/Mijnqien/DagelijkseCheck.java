package com.Mijnqien;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class DagelijkseCheck implements Runnable{
	ScheduledExecutorService sm;

	DagelijkseCheck(ScheduledExecutorService sm){
		this.sm=sm;
	}
	
	
	@Override
	public void run() {
		
		LocalDate vandaag = LocalDate.now();
		LocalDate evm = LocalDate.of(vandaag.getYear(), vandaag.getMonth(), 1);
		LocalDate evm2 = LocalDate.of(vandaag.getYear(), vandaag.plusMonths(1).getMonth(), 1);
		
		if(vandaag.isEqual(evm2.minusWeeks(1)))
			// mail reminder om urenlijsten up to date te maken
			
		if(vandaag.isEqual(evm2.minusDays(2)))
			
			// mail reminder om urenlijsten up to date te maken

		if(vandaag.isEqual(evm))
		// maak nieuwe urenlijsten aan;
		// reminder urenlijsten inleveren 
			
		if(vandaag.isEqual(evm.plusDays(3))) {}
		// laatse reminder volgende mail cc naar cora
		
		if(vandaag.isEqual(evm.plusDays(7))) {}
		// laatse reminder volgende mail cc naar cora

		System.out.println(evm2.minusWeeks(1));
		System.out.println(evm2.minusDays(2));
		System.out.println(evm.toString());
		System.out.println(evm.plusDays(3).toString());
	}
}
