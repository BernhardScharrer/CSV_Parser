package packets;

import org.gnet.packet.Packet;

import data.Table;

public class TableData extends Packet {

	private static final long serialVersionUID = -5606010197710292670L;

	public TableData(Table table, String params) {
		super("TABLEDATA", 2);
		super.addEntry("TABLE", table);
		super.addEntry("PARAMS", params);
	}
	
}
