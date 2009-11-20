package org.lopatka.idonc.computation.poc;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import org.lopatka.idonc.computation.IComputation;
import org.lopatka.idonc.model.data.IdoncLongData;
import org.lopatka.idonc.model.data.IdoncPart;
import org.lopatka.idonc.model.data.IdoncResult;

public class Poc2Computation implements IComputation {

	@Override
	public List<IdoncResult> computeData(IdoncPart part) {
		List<IdoncLongData> list = part.getLongDataList();
		long resultValue = 0;
		for (IdoncLongData data : list) {
			try {
				final long val = Long.parseLong(data.getValue());

				System.out.println("sleeping: "+val);
				waiting(val);
				System.out.println("waking");

				resultValue += val;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		IdoncResult result = new IdoncResult();
		result.setValue(Long.toString(resultValue));

		List<IdoncResult> res = new ArrayList<IdoncResult>();
		res.add(result);
		return res;
	}

	@Override
	public boolean isResultConfirmationRequired() {
		return false;
	}

	private void waiting(long n) {
		long t0, t1;

		t0 = System.currentTimeMillis();

		do {
			t1 = System.currentTimeMillis();
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (t1 - t0 < n);
	}

}
