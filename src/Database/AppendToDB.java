package Database;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendToDB extends ObjectOutputStream {

	public AppendToDB(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		reset();
	}
}