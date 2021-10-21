package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.model.Serie;
import gt.gob.rgm.inv.util.ResponseRs;

public interface SerieService {
	
	public ResponseRs listSeries();
	public ResponseRs getSerie(String id);
	public ResponseRs addSerie(Serie Serie);
	public ResponseRs updateSerie(Serie Serie);
	public ResponseRs deleteSerie(String id);
}
