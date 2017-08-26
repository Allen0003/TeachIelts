package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import bo.IeltsBo;
import entity.ManuBar;

public class Util {

	public static ArrayList<ManuBar> setManaData(ArrayList<ManuBar> manuBarS) {

		HashMap<String, ManuBar> result = new HashMap<String, ManuBar>();
		ArrayList<ManuBar> result1 = new ArrayList<ManuBar>();
		for (ManuBar manuBar : manuBarS) {
			if (manuBar.getSubClass() == null || manuBar.getSubClass().equals("")) {
				result.put(manuBar.getValue(), manuBar);
			}
		}

		for (ManuBar manuBar : manuBarS) {
			if (manuBar.getSubClass() != null && !manuBar.getSubClass().equals("")) {
				if (result.get(manuBar.getSubClass()).getMySubs() == null) {
					result.get(manuBar.getSubClass()).setMySubs(new ArrayList<ManuBar>());
				}
				result.get(manuBar.getSubClass()).getMySubs().add(manuBar);
			}
		}

		Iterator<Entry<String, ManuBar>> it = result.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry) it.next();
			result1.add((ManuBar) pair.getValue());
			it.remove();
		}

		return result1;
	}

	public static void setCategorization() {
		ArrayList<ManuBar> result = null;
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getManuBars();
			for (ManuBar manubar : result) {
				Const.manuMap.put(manubar.getValue(), manubar.getName());
			}
		} catch (Exception e) {
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
		}
		if (bo != null) {
			try {
				bo.disconnect();
			} catch (Exception e) {

			}
		}
	}
}
