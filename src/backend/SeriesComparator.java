package backend;

import java.util.Comparator;

public class SeriesComparator implements Comparator <Series> {

	@Override
	public int compare(Series arg0, Series arg1) {
		// TODO Auto-generated method stub
		if(arg0.getAverageRating()<arg1.getAverageRating())
			return -1;
		else if(arg0.getAverageRating()>arg1.getAverageRating())
			return 1;
		else
			return 0;

	}

}
