package cyber.learning.project.client;

import com.google.gwt.user.client.ui.AbsolutePanel;

public class MySplitPanel {
	public AbsolutePanel north;
	public AbsolutePanel south;
	public AbsolutePanel east;
	public AbsolutePanel west;
	
	MySplitPanel()
	{
		north = new AbsolutePanel();
		south = new AbsolutePanel();
		east = new AbsolutePanel();
		west = new AbsolutePanel();
	}
}
